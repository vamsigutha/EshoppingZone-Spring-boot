import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header.component';
import { SharedModule } from '@shared/shared.module';
import { AppRoutingModule } from 'src/app/app-routing.module';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    AppRoutingModule
  ],
  declarations: [HeaderComponent],
  exports:[HeaderComponent]
})
export class HeaderModule { }
