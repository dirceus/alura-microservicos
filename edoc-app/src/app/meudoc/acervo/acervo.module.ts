import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AcervoComponent } from './acervo.component';
import { AcervoListaComponent } from './acervo-lista/acervo-lista.component';

@NgModule({
  declarations: [AcervoComponent, AcervoListaComponent],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [AcervoComponent, AcervoListaComponent]
})
export class AcervoModule { }
