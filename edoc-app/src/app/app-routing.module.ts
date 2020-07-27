import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NotFoundComponent } from './core/erros/not-found/not-found.component';
import { LoginComponent } from './home/login/login.component';
import { AuthGuard } from './core/auth/auth.guard';
import { CadastroComponent } from './home/cadastro/cadastro.component';
import { HomeComponent } from './home/home.component';
import { AcervoComponent } from './meudoc/acervo/acervo.component';
import { MeudocComponent } from './meudoc/meudoc.component';
import { TipologiaComponent } from './meudoc/tipologia/tipologia.component';
import { DocumentoComponent } from './meudoc/documento/documento.component';

const routes: Routes = [
  {
    path: '', component: HomeComponent, canActivate: [AuthGuard],
    children: [
      { path: '', component: LoginComponent, },
      { path: 'cadastro', component: CadastroComponent},
    ]
  },
  { path: 'meudoc', component: MeudocComponent },
  { path: 'meudoc/acervo', component: AcervoComponent },
  { path: 'meudoc/tipologia', component: TipologiaComponent },
  { path: 'meudoc/documento', component: DocumentoComponent },
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
