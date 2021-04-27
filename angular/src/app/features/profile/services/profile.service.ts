import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
getUrl="http://localhost:8765/api/v1/auth/get-user";
updateUrl="http://localhost:8765/api/v1/auth/update-user";
getOrdersUrl="http://localhost:8765/api/v1/order/get-order-by-user-id";
params;
headers;
token;
tokenPayload;
constructor(private http:HttpClient) { }

getUserData(){
  this.makeUrl();
  return this.http.get(this.getUrl,{'params':this.params,'headers':this.headers});
}

updateUser(data){
  this.makeUrl();
  return this.http.put(this.updateUrl,data,{'headers':this.headers});
}

getOrders(){
  this.makeUrl();
  this.params = new HttpParams().set("userId",this.tokenPayload["id"]);
  return this.http.get(this.getOrdersUrl,{'params':this.params,'headers':this.headers});
}

makeUrl(){
  this.token = localStorage.getItem("token");
  this.tokenPayload = decode(this.token);
  console.log(this.tokenPayload["id"]);
  this.params = new HttpParams().set("id",this.tokenPayload["id"]);
  this.headers = {'Authorization':`Bearer ${this.token}`};
}

}
