import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class SingleProductService {

url="http://localhost:8765/api/v1/cart/add-item";
params;

constructor(private http:HttpClient) { 
  
}

addToCart(data){
  const token = localStorage.getItem("token");
  const tokenPayload = decode(token);
  console.log(tokenPayload["id"]);
  this.params = new HttpParams().set("userId",tokenPayload["id"]);
  const headers = {'Authorization':`Bearer ${token}`};
  return this.http.post(this.url,data,{'params':this.params,'headers':headers});
}

}
