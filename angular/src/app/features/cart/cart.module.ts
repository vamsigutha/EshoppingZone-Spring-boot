import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartComponent } from './cart.component';
import { SharedModule } from '@shared/shared.module';
import { AppRoutingModule } from 'src/app/app-routing.module';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    AppRoutingModule
  ],
  declarations: [CartComponent]
})
export class CartModule { }
