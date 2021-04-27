import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchProductComponent } from './search-product.component';

import { AppRoutingModule } from 'src/app/app-routing.module';

@NgModule({
  imports: [
    CommonModule,
    AppRoutingModule
  ],
  declarations: [SearchProductComponent]
})
export class SearchProductModule { }
