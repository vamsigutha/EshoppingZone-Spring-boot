import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { ProductDetails } from '@core/models/product-details';
import { ProductDetailService } from './services/product-detail.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  productDetails:any=null;
  constructor(private ref: ChangeDetectorRef,private route:ActivatedRoute,private productDetailService:ProductDetailService) { }

  ngOnInit() {
    this.route.params.subscribe((params:Params)=>{
      this.productDetailService.getProduct(params.productId).subscribe(result=>{
        this.productDetails = result;
        this.ref.detectChanges();
        console.log(this.productDetails);
      });
      
    });
  }

}
