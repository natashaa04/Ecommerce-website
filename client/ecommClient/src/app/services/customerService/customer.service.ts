import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserStorageService } from '../storage/user-storage.service';
import { Observable } from 'rxjs';

const BASIC_URL = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient,userStorageService:UserStorageService) {}

  private createAuthorizationHeader(): HttpHeaders {

    // const token = AdminService.Token;
    const token = UserStorageService.getToken();
    console.log('token here',token) ;// Retrieve token from UserStorageService
    return new HttpHeaders().set('Authorization', 'Bearer ' + token).set('Content-Type', 'application/json'); // Add Content-Type header
  
  }
  getAllProducts():Observable<any>{
    return this.http.get( BASIC_URL+'api/admin/products',{
      headers:this.createAuthorizationHeader(),
    })
  }

getAllProductsByName(name:any):Observable<any>{
  return this.http.get(BASIC_URL+`api/admin/search/${name}`,{
  headers:this.createAuthorizationHeader(),
  }
  )
}

addToCart(productId: any): Observable<any> {
  const cartDto = {
    productId: productId,
    userId: UserStorageService.getUserId()
  };

  return this.http.post(BASIC_URL + 'api/customer/cart', cartDto, {
    headers: this.createAuthorizationHeader()
  });
}


}