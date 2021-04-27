import { HttpClient,HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductDetailService {

  url:string = "http://localhost:8765/api/v1/products/product-id";

constructor(private http:HttpClient) { }

getProduct(productId){
  let params = new HttpParams().set("productId",productId);
  return this.http.get(this.url,{params:params});
}

}
