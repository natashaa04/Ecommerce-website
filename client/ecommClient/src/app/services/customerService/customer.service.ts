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
console.log('cartdto',cartDto)
  return this.http.post(BASIC_URL + 'api/customer/cart', cartDto, {
    headers: this.createAuthorizationHeader()
  });
}

getCartByUserId(): Observable<any> {
  const userId = UserStorageService.getUserId();
  return this.http.get(`${BASIC_URL}api/customer/cart/${userId}`, {
      headers: this.createAuthorizationHeader()
  });
}


increaseProductQuantity(productId: any): Observable<any> {
  const cartoto = {
    productId: productId,
    userId: UserStorageService.getUserId()
  };

  return this.http.post(BASIC_URL + 'api/customer/addition', cartoto, {
    headers: this.createAuthorizationHeader()
  });
}
decreaseProductQuantity(productId: any): Observable<any> {
  const cartdto = {
    productId: productId,
    userId: UserStorageService.getUserId()
  };

  return this.http.post(BASIC_URL + 'api/customer/deduction', cartdto, {
    headers: this.createAuthorizationHeader()
  });
}



applyCoupon(code: any): Observable<any> {
  const userId = UserStorageService.getUserId();

  return this.http.get(`${BASIC_URL}api/customer/coupon/${userId}/${code}`, {
    headers: this.createAuthorizationHeader()
  });
}

placeOrder(orderDto: any): Observable<any> {
  orderDto.userId = UserStorageService.getUserId();

  return this.http.post(BASIC_URL + 'api/customer/placeOrder', orderDto, {
    headers: this.createAuthorizationHeader()
  });
}

getOrdersByUserId(): Observable<any> {

  const userId = UserStorageService.getUserId();
  return this.http.get(`${BASIC_URL}api/customer/myOrders/${userId}`, {
      headers: this.createAuthorizationHeader()
  });
}

getOrderedProducts(orderId:number):Observable<any>{
  return this.http.get(`${BASIC_URL}api/customer/ordered-products/${orderId}`, {
    headers: this.createAuthorizationHeader()
});
}




giveReview(reviewDto:any): Observable<any> {

  return this.http.post(`${BASIC_URL}api/customer/review`,reviewDto, {
      headers: this.createAuthorizationHeader()
  });
}

getProductDetailById(productId:number): Observable<any> {
  return this.http.get(`${BASIC_URL}api/customer/product/${productId}`, {
      headers: this.createAuthorizationHeader()
  });
}




}
