import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaymentComponent } from './payment.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CheckoutComponent } from './component/checkout/checkout.component';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { SuccessComponent } from './component/success/success.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  declarations: [PaymentComponent,CheckoutComponent,SuccessComponent],
  exports:[CheckoutComponent,SuccessComponent]
})
export class PaymentModule { }
