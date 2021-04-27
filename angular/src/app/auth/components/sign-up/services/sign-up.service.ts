import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  url:string = "http://localhost:8765/api/v1/auth/signup";
constructor(private http:HttpClient) { }

signup(data:any){
  
  return this.http.post(this.url,data);
}

}
