import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { AcervoModule } from './acervo/acervo.module';
import { TipologiaModule } from './tipologia/tipologia.module';
import { DocumentoModule } from './documento/documento.module';
import { MeudocComponent } from './meudoc.component';



@NgModule({
  declarations: [MeudocComponent],
  imports: [
    CommonModule,
    RouterModule,
    AcervoModule,
    TipologiaModule,
    DocumentoModule
  ]
})
export class MeudocModule { }
