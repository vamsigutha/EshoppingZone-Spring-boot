import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { ProductThumbnail } from '@core/models/product-thumbnail';
import { SearchProductService } from './services/search-product.service';

@Component({
  selector: 'app-search-product',
  templateUrl: './search-product.component.html',
  styleUrls: ['./search-product.component.css']
})
export class SearchProductComponent implements OnInit {

  products:Array<ProductThumbnail>=[];
  title:string;

  constructor(private route:ActivatedRoute,private searchService:SearchProductService) { }

  ngOnInit() {
    this.route.params.subscribe((params:Params)=>{
      this.title = params.title;
      this.searchService.getProducts(this.title).subscribe((res)=>{
        this.products = [];
        this.products = res["products"];
        console.log(res);
      });
    });
    
  }



}
