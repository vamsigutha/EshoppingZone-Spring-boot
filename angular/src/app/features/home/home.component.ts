import { Component, OnInit } from '@angular/core';
import { ProductThumbnail } from '@core/models/product-thumbnail';
import { HomeServiceService } from './services/home-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  products:Array<ProductThumbnail>=[];
  category="beauty";
  subCategory="Makeup";
  
  constructor(private homeService:HomeServiceService) { }

  ngOnInit() {
    this.homeService.getProductsByCategory(this.category,this.subCategory).subscribe(result=>{
      this.products = result["products"]; 
    },
    error=>console.log("Error occured"));
  }

  

}
