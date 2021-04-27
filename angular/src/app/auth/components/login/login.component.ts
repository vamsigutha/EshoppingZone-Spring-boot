import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import {  Router } from '@angular/router';
import { LoginServiceService } from './services/login-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form;
  data;

  constructor(fb:FormBuilder,private loginService:LoginServiceService,private router:Router) { 
    this.form = fb.group({
      "username":["",Validators.required],
      "password":["",Validators.required]
    })
  }

  ngOnInit() {
  }

  onSubmit(){
    this.data = {
      "username":this.form.value.username,
      "password":this.form.value.password
    }
    this.loginService.login(this.data).subscribe((res)=>{
      console.log(res);
      const { redirect } = window.history.state;
      localStorage.setItem('token',res["accessToken"]);
      this.router.navigateByUrl(redirect || '/');
    },(error)=>{
      console.log(error.error.message);
    })
  }

}
