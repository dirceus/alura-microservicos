import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DocumentoDetalhesComponent } from './documentos/documento-detalhes/documento-detalhes.component';
import { DocumentoFormComponent } from './documentos/documento-form/documento-form.component';
import { DocumentoListaComponent } from './documentos/documento-lista/documento-lista.component';
import { NotFoundComponent } from './core/erros/not-found/not-found.component';
import { LoginComponent } from './home/login/login.component';
import { AuthGuard } from './core/auth/auth.guard';
import { CadastroComponent } from './home/cadastro/cadastro.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {
    path: '', component: HomeComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: LoginComponent, },
      { path: 'cadastro', component: CadastroComponent},
    ]
  },
  { path: 'documento', component: DocumentoListaComponent },
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
