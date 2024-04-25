import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class UserStorageService {


  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
   if(typeof window !=='undefined')
{      console.log('window');
}
    }
  }
  

  public saveToken(token: string): void {
      window.localStorage.removeItem('TOKEN');
      window.localStorage.setItem('TOKEN', token);
  }
  
  public saveUser(user: any): void {
      window.localStorage.removeItem('USER');
      window.localStorage.setItem('USER', JSON.stringify(user));
  }
  static getToken(): string {
    if (typeof window === 'undefined') {
      // console.log('no window');
        return null; // or any appropriate value for your application
    }
    return window.localStorage.getItem('TOKEN');
}

static getUser(): any {
    if (typeof window === 'undefined') {
      // console.log('no window');
        return null; // or any appropriate value for your application
    }
    return window.localStorage.getItem('USER');
}

// Modify other static methods similarly...

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
  // console.log('customer token',this.getToken())
  const role: String = this.getUserRole();
  //  console.log('role is',role);
  return role === 'CUSTOMER';
}

static signOut(): void {
  window.localStorage.removeItem('TOKEN');
  window.localStorage.removeItem('USER');
}


}
