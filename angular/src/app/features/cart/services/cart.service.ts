import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class CartService {

getUrl="http://localhost:8765/api/v1/cart/get-cart";
deleteUrl="http://localhost:8765/api/v1/cart/delete-item";
updateUrl = "http://localhost:8765/api/v1/cart/update-item";
params;
headers;
token;
tokenPayload;
httpOptions;

constructor(private http:HttpClient) { }

getCart(){
 
  this.makeUrl();
  return this.http.get(this.getUrl,{'params':this.params,'headers':this.headers});

}

updateItem(item){
  this.makeUrl();
  this.httpOptions = {'params':this.params,'headers':this.headers,'body':item};
  return this.http.put(this.updateUrl,item,{'params':this.params,'headers':this.headers});
}

deleteItem(item){
  this.makeUrl();
  this.httpOptions = {'params':this.params,'headers':this.headers,'body':item}
  return this.http.delete(this.deleteUrl,this.httpOptions);
}

makeUrl(){
  this.token = localStorage.getItem("token");
  this.tokenPayload = decode(this.token);
  console.log(this.tokenPayload["id"]);
  this.params = new HttpParams().set("userId",this.tokenPayload["id"]);
  this.headers = {'Authorization':`Bearer ${this.token}`};
}

}
