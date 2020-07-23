import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { UserService } from '../user/user.service';
import { User } from '../user/user';

const API_URL = 'http://localhost:8080';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private userService: UserService) { }

  autenticar(email: string, senha: string){
    return this.http
      .post<User>(API_URL + '/auth', { email, senha })
      .pipe(tap(res => {
        this.userService.setUsuarioLogado(res);
        console.log('Usuário autenticado');
      }));
  }

}
