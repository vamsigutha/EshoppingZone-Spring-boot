import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { BlocksModule } from './blocks/blocks.module';
import { AppComponent } from './blocks/root/app.component';
import {HttpClientModule} from '@angular/common/http';
import { CoreModule } from '@core/core.module';
import { SharedModule } from '@shared/shared.module';
import { AuthModule } from './auth/auth.module';
import {ReactiveFormsModule} from "@angular/forms";
import { CartModule } from './features/cart/cart.module';
import { ProfileModule } from './features/profile/profile.module';
import { SearchProductModule } from './features/search-product/search-product.module';
import { HomeModule } from './features/home/home.module';
import { PaymentModule } from './features/payment/payment.module';
import { DeliveryAgentModule } from './features/delivery-agent/delivery-agent.module';

@NgModule({
  declarations: [
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BlocksModule,
    HttpClientModule,
    CoreModule,
    AuthModule,
    ReactiveFormsModule,
    CartModule,
    ProfileModule,
    SearchProductModule,
    HomeModule,
    PaymentModule,
    DeliveryAgentModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  exports:[AppRoutingModule,CoreModule]
})
export class AppModule { }
