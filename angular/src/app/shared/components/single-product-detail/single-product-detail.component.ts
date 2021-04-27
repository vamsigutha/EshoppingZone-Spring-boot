import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductDetails } from '@core/models/product-details';
import { AuthGuardService } from 'src/app/auth/services/auth-guard.service';
import { SingleProductService } from './services/single-product.service';

@Component({
  selector: 'app-single-product-detail',
  templateUrl: './single-product-detail.component.html',
  styleUrls: ['./single-product-detail.component.css']
})
export class SingleProductDetailComponent implements OnInit, OnChanges {

  @Input() product:ProductDetails;

  currentImage:string;
  images:Array<string>;
  data;

  constructor(private singleProductService:SingleProductService,private auth:AuthGuardService,private router:Router) { 
   
    
  }

  ngOnInit() {
    
  }

  ngOnChanges(){
    console.log(this.product);
    this.currentImage = this.product?.images[0];
    this.images = this.product.images;
    console.log(this.images);
  }

  changeCurrentImage(event){
    this.currentImage = event.target.src;
  }

  onClickAddToCartButton(event){
    if(this.auth.canActivate()){
      this.data = {
        "productId":this.product.id,
        "title":this.product.title,
        "image":this.images[0],
        "price":this.product.price,
        "quantity":1
      }
      this.singleProductService.addToCart(this.data).subscribe((res)=>{
        event.target.innerText = "Added to Cart";
        console.log(res);
      },(error)=>{
        console.log(error);
      })
    }
    
  }

}
