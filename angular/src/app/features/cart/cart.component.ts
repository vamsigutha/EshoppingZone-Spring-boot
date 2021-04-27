import { Component, OnChanges, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from '@core/models/cart';
import { CartService } from './services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit{

  cart:Cart;

  constructor(private cartService:CartService, private router:Router) { }

  ngOnInit() {
    this.cartService.getCart().subscribe((res:Cart)=>{
      this.cart=res;
      console.log(this.cart);
    }); 
  }

  onClickCheckout(){
    localStorage.setItem("cart",JSON.stringify(this.cart));
    this.router.navigate(["/","checkout"]);
  }

  increaseQuantity(item){
    item.quantity = item.quantity+1;
    console.log(item.quantity);
    this.cartService.updateItem(item).subscribe((res)=>{
      console.log(res);
      this.updateCart(res);
    });
  }

  decreaseQuantity(item){
    item.quantity = item.quantity-1;
    console.log(item.quantity);
    this.cartService.updateItem(item).subscribe((res)=>{
      console.log(res);
      this.updateCart(res);
    });
  }

  onClickItem(productId){
    
    this.router.navigate(["/","category","subCategory",productId]);
  }


  onClickDeleteItem(item){
    this.cartService.deleteItem(item).subscribe((res)=>{
      this.updateCart(res);
    });
  }

  updateCart(res){
    this.cart.id = res["id"];
      this.cart.userId = res["userId"];
      this.cart.items = res["items"];
      this.cart.totalPrice = res["totalPrice"];
      this.cart.totalSavingsAmount = res["totalSavingsAmount"];
      console.log(this.cart);
  }

}
