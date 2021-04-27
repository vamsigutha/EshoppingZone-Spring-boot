import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  userUrl="http://localhost:8765/api/v1/auth/get-user";
  token;
  tokenPayload;
  headers;

constructor(private http:HttpClient) { }

chargeCard(token,address,user) {
  this.makeToken();
  // const params = new HttpParams().set("token",token).set("amount","100").set("userId",this.tokenPayload["id"]);
  const body = {
    token:token,
    address:address,
    userId:user.id,
    mobileNumber:user.mobileNumber,
    email:user.email
  }
  return this.http.post('http://localhost:8765/api/v1/cart/charge',body);
    
}

getUser(){
  this.makeToken();
  const params = new HttpParams().set("id",this.tokenPayload["id"]);
  return this.http.get(this.userUrl,{'params':params,'headers':this.headers});
}

makeToken(){
  this.token = localStorage.getItem("token");
  this.tokenPayload = decode(this.token);
  this.headers = {'Authorization':`Bearer ${this.token}`};
  console.log(this.tokenPayload["id"]);
}

}
