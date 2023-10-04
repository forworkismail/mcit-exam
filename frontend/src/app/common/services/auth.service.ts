import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class AuthService {
  constructor(private http: HttpClient) {}

  login(email: string, password: string) {
    return this.http.post('/api/auth/login', { email, password });
  }

  isLoggedIn() {
    return localStorage.getItem('token') !== null;
  }

  logOut() {
    localStorage.removeItem('token');
  }
}
