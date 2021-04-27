import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HomeServiceService {
  url:string = "http://localhost:8765/api/v1/products/category";
constructor(private http:HttpClient) { }


getProductsByCategory(category,subCategory){
  
  let params = new HttpParams().set("category",category).set("subCategory",subCategory).set("page","6");

  return this.http.get(this.url,{params:params});
}

}
