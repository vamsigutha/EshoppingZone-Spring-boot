import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductcategoryComponent } from './productcategory.component';
import { SharedModule } from '@shared/shared.module';
import { BlocksModule } from 'src/app/blocks/blocks.module';

@NgModule({
  imports: [
    CommonModule,
    SharedModule
  ],
  declarations: [ProductcategoryComponent]
})
export class ProductcategoryModule { }
