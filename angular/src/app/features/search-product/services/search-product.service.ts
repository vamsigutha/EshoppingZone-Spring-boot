import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SearchProductService {
url="http://localhost:8765/api/v1/products/";
constructor(private http:HttpClient) { }

getProducts(title){
  let params = new HttpParams().set("title",title);
  return this.http.get(this.url,{'params':params});
}

}
