import { Component, OnChanges, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { ProductThumbnail } from '@core/models/product-thumbnail';
import { Observable } from 'rxjs';
import { ProductCategoryService } from './services/product-category.service';

@Component({
  selector: 'app-productcategory',
  templateUrl: './productcategory.component.html',
  styleUrls: ['./productcategory.component.css']
})
export class ProductcategoryComponent implements OnInit {

  products:Array<ProductThumbnail>=[];
  totalItems:number;
  currentPageItems:number;
  totalPages:number;
  currentPage:number;
  category:string;
  subCategory:string;

  constructor(private productCategoryService:ProductCategoryService,private route:ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe((params:Params)=>{
      this.category = params.category;
      this.subCategory = params.subCategory;
      this.performRequest();
    });
  }

  onPrevPage(){
    if(this.currentPage != 1){
      this.productCategoryService.page = `${this.currentPage-1}`;
      this.performRequest();
      window.scrollTo(0,0);
    }
  }

  onNextPage(){
    if(this.currentPage < this.totalPages ){
      this.productCategoryService.page = `${this.currentPage+1}`;
      this.performRequest();
      window.scrollTo(0,0);
    }
  }

  performRequest(){
    this.productCategoryService.getProductsByCategory(this.category,this.subCategory).subscribe(result=>{
      this.products = result["products"];
      this.totalItems = result["totalItems"];
      this.currentPageItems = result["currentPageItems"];
      this.totalPages = result["totalPages"];
      this.currentPage = result["currentPage"];
      
    },
    error=>console.log("Error occured"));
  }


}
