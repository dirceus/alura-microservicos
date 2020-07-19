import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DocumentoDetalhesComponent } from './documentos/documento-detalhes/documento-detalhes.component';
import { DocumentoFormComponent } from './documentos/documento-form/documento-form.component';
import { DocumentoListaComponent } from './documentos/documento-lista/documento-lista.component';
import { NotFoundComponent } from './erros/not-found/not-found.component';

const routes: Routes = [
  { path: '', component: DocumentoListaComponent },
  { path: 'documento/adicionar', component: DocumentoFormComponent },
  { path: 'documento/detalhes/:docId', component: DocumentoDetalhesComponent },
  { path: 'documento/edit/:docId', component: DocumentoFormComponent },
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
