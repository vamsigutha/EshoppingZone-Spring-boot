import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedRoutingModule } from './shared-routing.module';
import { LogoComponent } from './components/logo/logo.component';
import { ChevronDownComponent } from './components/chevron-down/chevron-down.component';
import { ProductThumbnailCardComponent } from './components/product-thumbnail-card/product-thumbnail-card.component';
import { FilledRatingStarComponent } from './components/filled-rating-star/filled-rating-star.component';
import { NonFilledRatingStarComponent } from './components/non-filled-rating-star/non-filled-rating-star.component';
import { SingleProductDetailComponent } from './components/single-product-detail/single-product-detail.component';
import { UserCircleComponent } from './components/user-circle/user-circle.component';
import { TrashIconComponent } from './components/trash-icon/trash-icon.component';
import { CarouselComponent } from './components/carousel/carousel.component';
import { FooterComponent } from './components/footer/footer.component';


@NgModule({
  declarations: [
    LogoComponent,
    ChevronDownComponent,
    ProductThumbnailCardComponent,
    FilledRatingStarComponent,
    NonFilledRatingStarComponent,
    SingleProductDetailComponent,
    UserCircleComponent,
    TrashIconComponent,
    CarouselComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    SharedRoutingModule
  ],
  exports:[
    LogoComponent,
    ChevronDownComponent,
    ProductThumbnailCardComponent,
    FilledRatingStarComponent,
    NonFilledRatingStarComponent,
    SingleProductDetailComponent,
    UserCircleComponent,
    TrashIconComponent,
    CarouselComponent,
    FooterComponent
  ]
})
export class SharedModule { }
