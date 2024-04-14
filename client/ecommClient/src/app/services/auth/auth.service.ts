import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { UserStorageService } from '../storage/user-storage.service';
import { error } from 'console';

const BASE_URL = "http://localhost:8080/";

@Injectable({
  providedIn: "root"
})
export class AuthService {
  constructor(
    private http: HttpClient,
    private userStorageService: UserStorageService
  ) {}

  register(signupRequest: any): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post(BASE_URL + "sign-up", signupRequest, { headers, observe: 'response' });
  }

  login(username: string, password: string): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const body = { username, password };

    return this.http.post(BASE_URL + 'authenticate', body, { headers, observe: 'response' }).pipe(
      map((res:any) => {
        const token = res.headers.get('Authorization')?.substring(7);
        const user = res.body;
         console.log('login res',res);
        if (token && user) {
          this.userStorageService.saveToken(token);
          this.userStorageService.saveUser(user);
          return true;
        }

        return false;
      }), 
      catchError((err) => {
        console.log('login error',err);
        return of(false); 
      })
    );
  }
}
