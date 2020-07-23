import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/core/user/user';
import { CadastroService } from './cadastro.service';

import { EmailNaoExisteValidatorService } from './email-nao-existe.validator.service';

@Component({
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  cadastroForm: FormGroup;


  constructor(private formBuilder: FormBuilder,
              private emaiNaoExisteValidatorService: EmailNaoExisteValidatorService,
              private cadastroService: CadastroService,
              private router: Router
    ) { }

  ngOnInit(): void {
    this.cadastroForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email], this.emaiNaoExisteValidatorService.checarEmailExiste() ],
      nome: ['', [Validators.required , Validators.minLength(2), Validators.maxLength(100)]],
      senha: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(20)]]
    });
  }

  cadastrarUsuario() {
    console.log('Iniciando cadastro de usuário');
    const novoUsuario = this.cadastroForm.getRawValue() as User;
    this.cadastroService.cadastrarUsuario(novoUsuario)
      .subscribe(() => this.router.navigate(['']));
  }

}
