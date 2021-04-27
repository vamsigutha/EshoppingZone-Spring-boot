import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Address } from '@core/models/address';
import { User } from '@core/models/user';

import { PaymentService } from './services/payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  paymentForm;
  address: Address;
  user: User;
  paymentStatus="pay"

  constructor(private ref: ChangeDetectorRef,fb: FormBuilder, private paymentService: PaymentService, private router: Router) {
    this.paymentForm = fb.group({
      "cardNumber": ["", Validators.required],
      "expMonth": ["", Validators.required],
      "expYear": ["", Validators.required],
      "cvv": ["", Validators.required]
    });
  }

  ngOnInit() {
    this.address = JSON.parse(localStorage.getItem("address"));
    this.user = JSON.parse(localStorage.getItem("user"));
    console.log(this.address);
    console.log(this.user);
  }

  onClickCancelPayment() {
    this.router.navigate(["cart"]);
  }

  chargeCreditCard() {
    this.paymentStatus="processing";
        this.ref.detectChanges();
    (<any>window).Stripe.card.createToken({
      number: this.paymentForm.value.cardNumber,
      exp_month: this.paymentForm.value.expMonth,
      exp_year: this.paymentForm.value.expYear,
      cvc: this.paymentForm.value.cvc
    }, (status: number, response: any) => {
      if (status === 200) {
        let token = response.id;
        
        console.log(this.paymentStatus);
        this.paymentService.chargeCard(token, this.address, this.user).subscribe(res => {
          console.log(res);
          this.paymentStatus="success";
          this.ref.detectChanges();
          this.router.navigate(["/","success"]);
        },(error)=>{
          console.log(error);
          this.paymentStatus="failed";
          this.ref.detectChanges();
        });
      } else {
        console.log(response.error.message);
        this.paymentStatus="failed";
          this.ref.detectChanges();
      }
    });
  }



}
