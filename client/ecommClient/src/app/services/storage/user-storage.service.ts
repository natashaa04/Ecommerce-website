import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() {}

  public saveToken(token: string): void {
      window.localStorage.removeItem('TOKEN');
      window.localStorage.setItem('TOKEN', token);
  }
  
  public saveUser(user: any): void {
      window.localStorage.removeItem('USER');
      window.localStorage.setItem('USER', JSON.stringify(user));
  }
  static getToken(): String {

    return localStorage.getItem('TOKEN');
}

static getUser():any {

    return localStorage.getItem('USER');
}

static getUserId(): String {
    const user = this.getUser();
    if (!user) {
        return '';
    }
    return user.userId;
}

static getUserRole(): String {
    const user = JSON.parse(this.getUser());
    // console.log('user is',user,user.role)
    if (!user) {
        return '';
    }
    return user.role;
}

static isAdminLoggedIn(): boolean {
  if (this.getToken() === null) {
      return false;
  }
  const role: String = this.getUserRole();
  return role === 'ADMIN';
}

static isCustomerLoggedIn(): boolean {
  if (this.getToken() === null) {
      return false;
  }
  console.log('customer token',this.getToken())
  const role: String = this.getUserRole();
  //  console.log('role is',role);
  return role === 'CUSTOMER';
}

static signOut(): void {
  window.localStorage.removeItem('TOKEN');
  window.localStorage.removeItem('USER');
}


}
