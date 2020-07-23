import { Injectable } from '@angular/core';
import { AbstractControl } from '@angular/forms';
import { CadastroService } from './cadastro.service';
import { debounceTime, switchMap, map, first } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EmailNaoExisteValidatorService {

  constructor(private cadastroService: CadastroService) { }

  checarEmailExiste() {

    console.log('Verificando se email existe');

    return (control: AbstractControl) => {
      return control.valueChanges
        .pipe(debounceTime(300))
        .pipe(switchMap(email => this.cadastroService.verificarEmailJaExiste(email)))
        .pipe(map(emailExiste =>
          emailExiste ? { emailExiste: true } : null))
        .pipe(first());
    };
  }

}
