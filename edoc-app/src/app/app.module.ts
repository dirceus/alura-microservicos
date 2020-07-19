import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { DocumentosModule } from './documentos/documentos.module';
import { ErrosModule } from './erros/erros.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ErrosModule,
    DocumentosModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
