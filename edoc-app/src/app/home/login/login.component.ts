import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../../core/auth/auth.service';

@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  login(): void {
    const email = this.loginForm.get('email').value;
    const senha = this.loginForm.get('senha').value;
    this.authService.autenticar(email, senha).subscribe(
      () => this.router.navigate(['meudoc']),
      err => {
        alert('Login e Senha inválidos');
        console.log(err);
        this.loginForm.reset();
      }
    );

  }


}
