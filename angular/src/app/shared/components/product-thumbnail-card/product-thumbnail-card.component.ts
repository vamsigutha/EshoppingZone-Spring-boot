import { Component, Input, OnInit } from '@angular/core';
import { ProductThumbnail } from '@core/models/product-thumbnail';

@Component({
  selector: 'app-product-thumbnail-card',
  templateUrl: './product-thumbnail-card.component.html',
  styleUrls: ['./product-thumbnail-card.component.css']
})
export class ProductThumbnailCardComponent implements OnInit {

  @Input() product:ProductThumbnail;
  category:string;
  subCategory:string;
  id:string;

  constructor() { }

  ngOnInit() {
    console.log(this.product);
    // this.category = this.product.category.name;
    // this.subCategory = this.product.category.sub_category;
    // this.id = this.product.id;
  }

  onImageMouseOver(event){
    
    event.target.src = this.product.images[1];
  }

  onImageMouseOut(event){
    
    event.target.src = this.product.images[0];
  }

  showProduct(){
    
  }

}
