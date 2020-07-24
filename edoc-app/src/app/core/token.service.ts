import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  hasToken(): boolean {
    return !!this.getToken();
  }

  setToken(authToken): void {
    window.localStorage.setItem('authToken', authToken);
  }

  getToken() {
    return window.localStorage.getItem('authToken');
  }

  removeToken() {
    return window.localStorage.removeItem('authToken');
  }



}
