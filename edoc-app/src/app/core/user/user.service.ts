import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { TokenService } from '../token.service';
import { User } from './user';
import * as jtw_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userSubject = new BehaviorSubject<User>(null);
  private usuarioLogado: User;

  constructor(private tokenService: TokenService) {
    if (this.tokenService.hasToken()) {
      this.setUsuarioLogadoPeloToken(this.tokenService.getToken());
    }
  }

  logout(): void{
    this.tokenService.removeToken();
    this.userSubject.next(null);
    this.usuarioLogado = null;
  }

  setUsuarioLogado(usuario: User): void {
    this.usuarioLogado = usuario;
    this.tokenService.setToken(usuario.token);
    this.notificarUsuario(usuario);
    console.log('Usuário logado: ' + usuario.nome);
  }

  setUsuarioLogadoPeloToken(token): void{
    const decodedToken = jtw_decode(token);
    const user = JSON.parse(decodedToken['sub']) as User;
    user.token = token;
    this.setUsuarioLogado(user);
  }

  getUsuarioLogado(): User{
    return this.usuarioLogado;
  }

  getUsuarioObservable(): Observable<User> {
    return this.userSubject.asObservable();
  }

  isUsuarioLogado(): boolean {
    return this.tokenService.hasToken();
  }

  private notificarUsuario(usuario): void {
    this.userSubject.next(usuario);
  }

  

}
