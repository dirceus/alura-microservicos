import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/core/user/user';

const API_URL = 'http://localhost:8080/';
@Injectable({
  providedIn: 'root'
})
export class CadastroService {

  constructor(private http: HttpClient) { }

  verificarEmailJaExiste(email: string) {
    console.log('Iniciando chamada para verificar se email já existe');
    return this.http.get(API_URL + 'usuario/existe-email/' + email);
  }

  cadastrarUsuario(usuario: User) {
    console.log('Disparando post de registro do usuario: ' + usuario.nome);
    return this.http.post(API_URL + 'usuario/registrar', usuario);
  }


}
