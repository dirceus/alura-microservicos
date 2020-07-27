import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DocumentoComponent } from './documento.component';



@NgModule({
  declarations: [DocumentoComponent],
  imports: [
    CommonModule
  ],
  exports: [DocumentoComponent]
})
export class DocumentoModule { }
