import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserStorageService } from '../storage/user-storage.service';
import { Observable } from 'rxjs';

const BASIC_URL = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient,userStorageService:UserStorageService) {}

  // static getToken(token:String){
  //   // console.log('admins ervice token',token)
  //   AdminService.Token=token;
  // }
  

  private createAuthorizationHeader(): HttpHeaders {

    // const token = AdminService.Token;
    const token = UserStorageService.getToken();
    console.log('token here',token) ;// Retrieve token from UserStorageService
    return new HttpHeaders().set('Authorization', 'Bearer ' + token).set('Content-Type', 'application/json'); // Add Content-Type header
  
  }


  addCategory(categoryDto: any): Observable<any> {
    // console.log('token is',AdminService.Token);
    return this.http.post(BASIC_URL + 'api/admin/category', categoryDto, {
      headers: this.createAuthorizationHeader()
    
    });

  }
  getAllCategories():Observable<any>{
    return this.http.get( BASIC_URL+'api/admin/categories',{
      headers:this.createAuthorizationHeader(),
    })
  }

  addProduct(productDto:any):Observable<any>{
    console.log('product dto is',productDto);
     return this.http.post(BASIC_URL+'api/admin/product',productDto,{
      headers:this.createAuthorizationHeader(),
     })
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

  deleteProduct(productId: any): Observable<any> {
    return this.http.delete(`${BASIC_URL}/api/admin/product/${productId}`, {
        headers: this.createAuthorizationHeader()
    });
}


  
  
}
