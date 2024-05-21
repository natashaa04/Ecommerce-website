import { Component } from '@angular/core';
import { CustomerService } from '../../../services/customerService/customer.service';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { NgFor, NgIf } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserStorageService } from '../../../services/storage/user-storage.service';

@Component({
  selector: 'app-view-wish-list',
  standalone: true,
  imports: [AngularMaterialModule,NgIf,NgFor],
  templateUrl: './view-wish-list.component.html',
  styleUrl: './view-wish-list.component.scss'
})
export class ViewWishListComponent {
    
  products:any[]=[];

  constructor(private customerService:CustomerService,  private snackBar:MatSnackBar,){}

  ngOnInit(){
  this.getWishlistByUserId();
  }
  getWishlistByUserId(){
    this.customerService.getWishlistByUserId().subscribe({
      next:(res)=>{
        console.log('wishlist is',res)
        res.forEach(element=>{
          element.processedImg=element.img;
          this.products.push(element);
        });
      },error:(err)=>{
        console.log('error while fetching wishlist');
      }
    })
  }

  removeFromWishlist(productId:number){
    const cartdto = {
      productId: productId,
      userId: UserStorageService.getUserId()
    };
  
this.customerService.removeProductFromWishlist(cartdto).subscribe({
  next:(res)=>{
    this.snackBar.open("Product Removed From Wishlist Successfully", "Close", { duration: 5000 });
    this.products = this.products.filter(product => product.productId !== productId);
  },
  error:(err)=>{
    this.snackBar.open("Error While Removing From Wishlist","Close", { duration: 5000 })
  }
})
  }

  addToCart(productId:any){
    this.customerService.addToCart(productId).subscribe({
      next:(res) => {
        const cartdto = {
          productId: productId,
          userId: UserStorageService.getUserId()
        };
  this.customerService.removeProductFromWishlist(cartdto).subscribe({next:(res)=>{}})
      this.snackBar.open("Product Added To Cart Successfully", "Close", { duration: 5000 });
      
      },
      error:(err)=>{
        this.snackBar.open("Error Whle Adding To Cart","Close", { duration: 5000 })
      }
    });
  }
}
