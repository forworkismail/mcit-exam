import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class AuthService {
  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    return this.http.post('/api/auth/login', { username, password });
  }

  isLoggedIn() {
    return localStorage.getItem('role') !== null;
  }

  logOut() {
    localStorage.removeItem('role');
  }

  addToLocalStorage(key: string, value: string) {
    return localStorage.setItem(key, value);
  }
}
