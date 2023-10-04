import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LetterService {
  private apiUrl = '/api/letters'; // Modify this if your API endpoint is different

  constructor(private http: HttpClient) {}

  createLetter(type: string, content: string, userId: number, responseToId: number): Observable<any> {
    const body = {
      type: type,
      content: content,
      userId: userId,
      responseToId: responseToId,
    };
    return this.http.post(`${this.apiUrl}/create`, body);
  }

  getLetterById(letterId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${letterId}`);
  }

  getLettersByStatus(status: 'DRAFT' | 'APPROVED'): Observable<any> {
    return this.http.get(`${this.apiUrl}/status/${status}`);
  }

  approveLetter(letterId: number): Observable<any> {
    return this.http.patch(`${this.apiUrl}/${letterId}/approve`, {});
  }

  getLettersByType(type: 'INCOMING' | 'OUTGOING'): Observable<any> {
    return this.http.get(`${this.apiUrl}/type/${type}`);
  }
}
