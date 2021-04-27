import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BlocksRoutingModule } from './blocks-routing.module';
import { AppComponent } from './root/app.component';
import { SharedModule } from '@shared/shared.module';
import { HeaderModule } from '../features/header/header.module';
import { ProductcategoryModule } from '../features/productcategory/productcategory.module';
import { CategoryLoadingComponent } from './components/category-loading/category-loading.component';
import { ProductDetailModule } from '../features/product-detail/product-detail.module';
import { SearchProductModule } from '../features/search-product/search-product.module';



@NgModule({
  declarations: [AppComponent,CategoryLoadingComponent],
  imports: [
    CommonModule,
    BlocksRoutingModule,
    SharedModule,
    HeaderModule,
    ProductcategoryModule,
    ProductDetailModule
  ],
  exports:[AppComponent,CategoryLoadingComponent]
  
})
export class BlocksModule {  }
