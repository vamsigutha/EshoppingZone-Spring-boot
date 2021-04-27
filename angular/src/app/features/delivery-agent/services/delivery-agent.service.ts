import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class DeliveryAgentService {

  getUrl="http://localhost:8765/api/v1/order/get-order-by-not-assigned-delivery-agent";
  updateUrl="http://localhost:8765/api/v1/order/update-order";
  params;
  headers;
  token;
  tokenPayload;

constructor(private http:HttpClient) { }

getOrdersByNotAssigned(){
  this.makeUrl();
  return this.http.get(this.getUrl,{'headers':this.headers});
}

acceptOrder(order){
  this.makeUrl();
  order["deliveryAgentId"]=this.tokenPayload["id"];
  order["deliveryAgentAssigned"]=true;
  return this.http.post(this.updateUrl,order,{'headers':this.headers});
}

makeUrl(){
  this.token = localStorage.getItem("token");
  this.tokenPayload = decode(this.token);
  console.log(this.tokenPayload["id"]);
  this.params = new HttpParams().set("id",this.tokenPayload["id"]);
  this.headers = {'Authorization':`Bearer ${this.token}`};
}
}
