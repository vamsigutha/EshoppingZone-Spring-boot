import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Address } from '@core/models/address';
import { User } from '@core/models/user';
import { ProfileService } from './services/profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profileForm;
  addressForm;
  data:User;
  address:Address;
  addressList;

  ordersData;

  constructor(fb:FormBuilder,private router:Router,private profileService:ProfileService) {
    this.profileForm = fb.group({
      "username":["",Validators.required],
      "email":["",Validators.required],
      "mobileNumber":["",Validators.required]
    });

    this.addressForm = fb.group({
      "houseNumber":[""],
      "streetName":[""],
      "colonyName":["",Validators.required],
      "city":["",Validators.required],
      "state":["",Validators.required],
      "pincode":["",Validators.required]
    });
   }

   onProfileSubmit(){
     this.data["username"] = this.profileForm.value.username;
     this.data["email"]=this.profileForm.value.email;
     this.data["mobileNumber"]=this.profileForm.value.mobileNumber;
     this.profileService.updateUser(this.data).subscribe((res:User)=>{
       this.setProfileData(res);
     })
   }

   onAddressSubmit(){
     console.log(this.addressForm.value.houseNumber);
     this.address={
      houseNumber:this.addressForm.value.houseNumber,
      streetName:this.addressForm.value.streetName,
      colonyName:this.addressForm.value.colonyName,
      city:this.addressForm.value.city,
      state:this.addressForm.value.state,
      pincode:this.addressForm.value.pincode
     };
    // this.address["houseNumber"] = this.addressForm.value.houseNumber;
    // this.address.streetName = this.addressForm.value.streetName;
    // this.address.colonyName = this.addressForm.value.colonyName;
    // this.address.city = this.addressForm.value.city;
    // this.address.state = this.addressForm.value.state;
    // this.address.pincode = this.addressForm.value.pincode;

    this.addressList.push(this.address);
    this.data.addressList = this.addressList;

    this.profileService.updateUser(this.data).subscribe((res:User)=>{
      this.addressList = res.addressList;
      this.data = res;
    })

   }

  ngOnInit() {
    this.profileService.getUserData().subscribe((res:User)=>{
      this.setProfileData(res);
      this.addressList = res.addressList;
    });
    this.loadOrders();
  }

  setProfileData(res:User){
    this.profileForm.controls['username'].setValue(res["username"]);
      this.profileForm.controls['email'].setValue(res["email"]);
      this.profileForm.controls['mobileNumber'].setValue(res["mobileNumber"]);
      this.data=res;
      console.log(res);
  }

  loadOrders(){
    this.profileService.getOrders().subscribe((res)=>{
      this.ordersData = res;
      console.log(res);
    })
  }

  onClickSignOut(){
    localStorage.removeItem("token");
    this.router.navigate(['login']);

  }

}
