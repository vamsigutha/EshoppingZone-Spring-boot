package com.eshoppingzone.cartservice.cart.controllers;

import com.eshoppingzone.cartservice.cart.config.RabbitMQProperties;
import com.eshoppingzone.cartservice.cart.models.*;
import com.eshoppingzone.cartservice.cart.proxy.PaymentProxy;
import com.eshoppingzone.cartservice.cart.service.CartService;
import com.stripe.model.Charge;
import io.swagger.annotations.Api;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/cart")
@Api(value = "Cart Resource to handle all cart related actions and queries ")
public class CartController {

    CartService cartService;

    PaymentProxy paymentProxy;

    RabbitTemplate rabbitTemplate;

    RabbitMQProperties rabbitMQProperties;

    @Autowired
    public CartController(CartService cartService,PaymentProxy paymentProxy,RabbitTemplate rabbitTemplate,RabbitMQProperties rabbitMQProperties) {
        this.cartService = cartService;
        this.paymentProxy = paymentProxy;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQProperties = rabbitMQProperties;
    }

    //Add Item to Cart

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/add-item")
    public ResponseEntity<?> addItemToCart(@RequestBody Item item,
                                           @RequestParam String userId
                                           ){
        try{

            Cart cart = this.cartService.getCartByUserId(userId);

            if(cart!=null){
                List<Item> items = cart.getItems();
                items.add(item);
                cart.setItems(items);
                cart.setTotalPrice(cart.getTotalPrice() + item.getPrice().getCurrent_price()*item.getQuantity());
                cart.setTotalSavingsAmount(cart.getTotalSavingsAmount() + item.getPrice().getSavings_amount()*item.getQuantity());
            }else{
                List<Item> items = new ArrayList<>();
                items.add(item);

                cart = new Cart();
                cart.setUserId(userId);
                cart.setItems(items);
                cart.setTotalPrice(item.getPrice().getCurrent_price()*item.getQuantity());
                cart.setTotalSavingsAmount(item.getPrice().getSavings_amount()*item.getQuantity());
            }

            Cart updatedCart = this.cartService.addItemToCart(cart);

            return  new ResponseEntity<>(updatedCart,HttpStatus.CREATED);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get Cart By User Id

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/get-cart")
    public ResponseEntity<?> getCartByUserId(@RequestParam String userId){
        try{
            Cart cart = this.cartService.getCartByUserId(userId);
            if(cart != null){
                return new ResponseEntity<>(cart,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Cart is empty",HttpStatus.OK);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Update Item in Cart

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/update-item")
    public ResponseEntity<?> updateItemInCart(@RequestBody Item item,
                                              @RequestParam String userId){
        try{

            Cart cart = this.cartService.getCartByUserId(userId);
            List<Item> items = cart.getItems();

            Item previousItem = new Item();



            for (Item value : items) {
                if (value.getProductId().equals(item.getProductId())) {

                    previousItem.setProductId(value.getProductId());
                    previousItem.setTitle(value.getTitle());
                    previousItem.setImage(value.getImage());
                    previousItem.setPrice(value.getPrice());
                    previousItem.setQuantity(value.getQuantity());

                    value.setPrice(item.getPrice());
                    value.setQuantity(item.getQuantity());
                    value.setImage(item.getImage());
                    value.setTitle(item.getTitle());
                    break;

                }
            }

            cart.setItems(items);

            double price = cart.getTotalPrice()
                    - (previousItem.getPrice().getCurrent_price()*previousItem.getQuantity())
                    + (item.getPrice().getCurrent_price()*item.getQuantity());



            cart.setTotalPrice(price);

            cart.setTotalSavingsAmount(cart.getTotalSavingsAmount()
                    - (previousItem.getPrice().getSavings_amount()*previousItem.getQuantity())
                    + (item.getPrice().getSavings_amount()*item.getQuantity()));

            Cart updatedCart  = this.cartService.updateCart(cart);

            return  new ResponseEntity<>(updatedCart,HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete Item from Cart

    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/delete-item")
    public ResponseEntity<?> deleteItemFromCart(@RequestBody Item item,
                                                @RequestParam String userId
                                                ){
        try{
            Cart cart = this.cartService.getCartByUserId(userId);
            List<Item> items = cart.getItems();

//          items.removeIf(x->x.getProductId().equals(productId));


            items.remove(item);
            cart.setItems(items);
            cart.setTotalPrice(cart.getTotalPrice()-item.getPrice().getCurrent_price()*item.getQuantity());
            cart.setTotalSavingsAmount(cart.getTotalSavingsAmount()-item.getPrice().getSavings_amount()*item.getQuantity());

            Cart cartAfterDeleted = this.cartService.deleteItemFromCart(cart);

            return new ResponseEntity<>(cartAfterDeleted,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/delete-cart")
    public ResponseEntity<?> deleteCart(@RequestParam String userId){
        try{
            Cart cart = this.cartService.getCartByUserId(userId);
            cart.setTotalSavingsAmount(0);
            cart.setTotalPrice(0);
            List<Item> items = new ArrayList<>();
            cart.setItems(items);
            this.cartService.updateCart(cart);
            return new ResponseEntity<>(cart,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/charge")
    public ResponseEntity<?> chargeCard(@RequestBody OrderInfo orderInfo){
        //Connect to payments service with feign and after confirming the payment then send
        //items to queue and empty the cart

        try{
            Cart cart = this.cartService.getCartByUserId(orderInfo.getUserId());
            String paymentResponse = this.paymentProxy.makePayment(orderInfo.getToken(),cart.getTotalPrice()+"");


            Order order = new Order();
            order.setUserId(cart.getUserId());
            order.setTotalPrice(cart.getTotalPrice());
            order.setTotalSavingsAmount(cart.getTotalSavingsAmount());
            order.setItems(cart.getItems());
            order.setAddress(orderInfo.getAddress());
            order.setMobileNumber(orderInfo.getMobileNumber());
            order.setEmail(orderInfo.getEmail());


            rabbitTemplate.convertAndSend(this.rabbitMQProperties.getExchangeName(),this.rabbitMQProperties.getRoutingKey(),order);


            cart.setItems(new ArrayList<>());
            cart.setTotalPrice(0);
            cart.setTotalSavingsAmount(0);

            this.cartService.updateCart(cart);

            HashMap<String,Object> response = new HashMap<>();
            response.put("status",paymentResponse);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }







}
