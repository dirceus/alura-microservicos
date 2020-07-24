import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { UserComponent } from './user/user.component';
import { ErrosModule } from './erros/erros.module';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [HeaderComponent, UserComponent],
  imports: [
    CommonModule,
    ErrosModule,
    HttpClientModule,
    RouterModule
  ],
  exports: [
    HeaderComponent
  ]
})
export class CoreModule { }
