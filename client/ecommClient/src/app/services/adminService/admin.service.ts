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
    // console.log('product dto is',productDto);
     return this.http.post(BASIC_URL+'api/admin/product',productDto,{
      headers:this.createAuthorizationHeader(),
     })
  }

  getAllProducts():Observable<any>{
    return this.http.get( BASIC_URL+'products',{
      headers:this.createAuthorizationHeader(),
    })
  }

 


getAllProductsByName(name:any):Observable<any>{
  return this.http.get(BASIC_URL+`search/${name}`,{
  headers:this.createAuthorizationHeader(),
  }
  )
}

  deleteProduct(productId: any): Observable<any> {
    return this.http.delete(BASIC_URL+`api/admin/product/${productId}`, {
        headers: this.createAuthorizationHeader()
    });
}
addCoupon(couponDto: any): Observable<any> {
  return this.http.post(BASIC_URL + 'api/admin/coupons/create', couponDto, {
    headers: this.createAuthorizationHeader(),
  });
}

getCoupons(): Observable<any> {
  return this.http.get(BASIC_URL + 'api/admin/coupons/all', {
    headers: this.createAuthorizationHeader(),
  });
}
getPlacedOrders(): Observable<any> {
  return this.http.get(BASIC_URL + 'api/admin/placeOrders', {
    headers: this.createAuthorizationHeader(),
  });
}

changeOrderStatus(orderId:number,status:String): Observable<any> {
  return this.http.get(BASIC_URL + `api/admin/order/${orderId}/${status}`, {
    headers: this.createAuthorizationHeader(),
  });
}

postFAQ(productId:number,faqDto:any): Observable<any> {
  return this.http.post(BASIC_URL + `api/admin/faq/${productId}`, faqDto, {
    headers: this.createAuthorizationHeader(),
  });
}

updateProduct(productId,productDto:any):Observable<any>{
  // console.log('product dto is',productDto);
   return this.http.put(BASIC_URL+`api/admin/product/${productId}`,productDto,{
    headers:this.createAuthorizationHeader(),
   })
}

getProductById(productId):Observable<any>{
  return this.http.get( BASIC_URL+`product/${productId}`,{
    headers:this.createAuthorizationHeader(),
  })
}

getAnalytics():Observable<any>{
  return this.http.get( BASIC_URL+`api/admin/order/analytics`,{
    headers:this.createAuthorizationHeader(),
  })
}






  
  
}
