import { Component, OnInit } from '@angular/core';
import { Address } from '@core/models/address';
import { Cart } from '@core/models/cart';
import { User } from '@core/models/user';
import { PaymentService } from '../../services/payment.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  shippingAddress:Address;
  user:User;
  cart:Cart;

  constructor(private paymentService:PaymentService) { }

  ngOnInit() {
    
    console.log(JSON.parse(localStorage.getItem("cart")));
    this.cart = JSON.parse(localStorage.getItem("cart"));
    this.paymentService.getUser().subscribe((res:User)=>{
      this.user = res;
      console.log(res);
      this.shippingAddress = res.addressList[0];
    })
  }
  
  changeAddress(addressList){
    console.log(addressList.classList);
    addressList.classList="flex flex-col space-y-3";
    
  }

  selectedAddress(addressList,address){
    console.log(addressList);
    this.shippingAddress = address;
    addressList.classList="hidden";
    
  }

  onClickNext(){
    localStorage.setItem("address",JSON.stringify(this.shippingAddress));
    localStorage.setItem("user",JSON.stringify(this.user));
  }

}
