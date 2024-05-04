import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { CustomerService } from '../../../services/customerService/customer.service';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { PlaceOrderComponent } from '../place-order/place-order.component';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [AngularMaterialModule, FormsModule,
    ReactiveFormsModule,NgIf,CurrencyPipe,NgClass,NgFor],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})

export class CartComponent {
  cartItems: any[]=[];
  order: any;
  couponForm:FormGroup;
  constructor(
      private customerService: CustomerService,
      private snackbar: MatSnackBar,
      private fb: FormBuilder,
      public dialog: MatDialog,
  ) {
    
  }

  ngOnInit(){
    this.getCart();
    this.couponForm=this.fb.group({
      code:[null,[Validators.required]]
    })
   
    console.log('cartItems are',this.cartItems);
  }


  getCart() {
    this.cartItems = [];
    console.log('getCart');
    this.customerService.getCartByUserId().subscribe({
     next :(res) => {
      console.log('res of ggetCart',res);
        this.order = res;
        res.cartItemsDto.forEach(element => {
            element.processedImg = element.returnedImg;
            this.cartItems.push(element);
        });
    },
    error:(err)=>{
             console.log('error while fetching cart item',err);
    }
  }
    );
}



increaseQuantity(productId: any) {
  this.customerService.increaseProductQuantity(productId).subscribe({
    next:(res) => {
    this.snackbar.open('Product quantity increased.', 'Close', { duration: 5000 });
    this.getCart();
  }
,error:(err)=>{
    console.log('error while increasing quantity',err);
}
})
}

decreaseQuantity(productId: any) {
  this.customerService.decreaseProductQuantity(productId).subscribe({
    next:(res) => {
    this.snackbar.open('Product quantity decreased.', 'Close', { duration: 5000 });
    this.getCart();
  }
,error:(err)=>{
    console.log('error while decreasing quantity',err);
}
})
}

applyCoupon(){
  this.customerService.applyCoupon(this.couponForm.get(['code'])!.value).subscribe(
    {
      next:(res)=>{
       this.snackbar.open("Coupon Applied Sucessfully",'  Close',{
        duration:5000
       }) 
       this.getCart();
      },
      error:(err)=>{
        this.snackbar.open(err.error,'ERROR',{duration:5000});
      }
    }
  )}
placeOrder(){
  console.log('dialogg')
  this.dialog.open(PlaceOrderComponent);
}


}
