import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {
url:string = "http://localhost:8765/api/v1/auth/signin";
constructor(private http:HttpClient) { }

login(data){
  return this.http.post(this.url,data);
}

}
