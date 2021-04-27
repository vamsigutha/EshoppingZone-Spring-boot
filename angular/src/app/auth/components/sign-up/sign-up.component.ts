
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import {  Router } from '@angular/router';
import { SignUpService } from './services/sign-up.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  form;
  data;

  constructor(fb:FormBuilder,private signUpService:SignUpService,private router:Router) { 
    this.form = fb.group({
      "username":["",Validators.required],
      "email":["",Validators.required],
      "password":["",Validators.required]
    })
  }

  ngOnInit() {
    
  }

  onSubmit(){
    this.data = {
      "username":this.form.value.username,
      "email":this.form.value.email,
      "password":this.form.value.password
    }
    this.signUpService.signup(this.data).subscribe((res)=>{
      console.log(res);
      this.router.navigate(['/login']);
    },(error)=>{
      console.log(error.error.message);
    })
  }

}
