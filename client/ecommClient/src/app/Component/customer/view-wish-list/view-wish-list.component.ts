import { Component } from '@angular/core';
import { CustomerService } from '../../../services/customerService/customer.service';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-view-wish-list',
  standalone: true,
  imports: [AngularMaterialModule,NgIf,NgFor],
  templateUrl: './view-wish-list.component.html',
  styleUrl: './view-wish-list.component.scss'
})
export class ViewWishListComponent {
    
  products:any[]=[];

  constructor(private customerService:CustomerService){}

  ngOnInit(){
  this.getWishlistByUserId();
  }
  getWishlistByUserId(){
    this.customerService.getWishlistByUserId().subscribe({
      next:(res)=>{
        res.forEach(element=>{
          element.processedImg=element.img;
          this.products.push(element);
        });
      },error:(err)=>{
        console.log('error while fetching wishlist');
      }
    })
  }
}
