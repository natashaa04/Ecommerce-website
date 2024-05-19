import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from '../../../services/customerService/customer.service';
import { ActivatedRoute, RouterLink, RouterModule } from '@angular/router';
import { CurrencyPipe, DatePipe, NgFor, NgIf } from '@angular/common';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { UserStorageService } from '../../../services/storage/user-storage.service';

@Component({
  selector: 'app-view-product-detail',
  standalone: true,
  imports: [AngularMaterialModule,NgIf,NgFor,DatePipe,CurrencyPipe,RouterModule,RouterLink],
  templateUrl: './view-product-detail.component.html',
  styleUrl: './view-product-detail.component.scss'
})
export class ViewProductDetailComponent {
  productId: number = this.activatedRoute.snapshot.params["productId"];
  product: any;
  FAQS: any[] = [];
  reviews: any[] = [];
  
  constructor(private snackBar: MatSnackBar, 
    private customerService: CustomerService,
     private activatedRoute: ActivatedRoute) {}
  
  ngOnInit() {
      this.getProductDetailById();
  }
  
  getProductDetailById() {
      this.customerService.getProductDetailById(this.productId).subscribe({
        next:(res) => {
          this.product = res.productDto;
          this.product.processedImg =  res.productDto.img;
          this.FAQS = res.faqDtoList;
          res.reviewDtoList.forEach(element => {
              element.processedImg =  element.img;
              this.reviews.push(element);
          })}
          ,error:(err)=>{
             console.log('error while fetching product detail');
             
          }
      });
  
  }

  addToWishlist(){
    const wishlistDto={
      productId:this.productId,
      userId:UserStorageService.getUserId()
    }

    this.customerService.addProductToWishlist(wishlistDto).subscribe({
      next:(res)=>{
      this.snackBar.open('Product Added to Wishlist','Close',{
        duration:5000
      })
      },
      error:(err)=>{
        this.snackBar.open('Already in Wishlist','ERROR',{
          duration:5000
        });
      }
    })
  }
  
}
