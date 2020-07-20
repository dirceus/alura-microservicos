import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DocumentoListaComponent } from './documento-lista/documento-lista.component';
import { DocumentoDetalhesComponent } from './documento-detalhes/documento-detalhes.component';
import { DocumentoFormComponent } from './documento-form/documento-form.component';



@NgModule({
  declarations: [DocumentoListaComponent, DocumentoDetalhesComponent, DocumentoFormComponent],
  exports: [DocumentoListaComponent],
  imports: [
    CommonModule
  ]
})
export class DocumentosModule { }
