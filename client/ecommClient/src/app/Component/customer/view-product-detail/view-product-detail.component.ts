import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from '../../../services/customerService/customer.service';
import { ActivatedRoute, RouterLink, RouterModule } from '@angular/router';
import { CurrencyPipe, DatePipe, NgFor, NgIf } from '@angular/common';
import { AngularMaterialModule } from '../../../AngularMaterialModule';

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
      this.customerService.getProductDetailById(this.productId).subscribe(res => {
          this.product = res.productDto;
          this.product.processedImg =  res.productDto.img;
          this.FAQS = res.faqDtoList;
          res.reviewDtoList.forEach(element => {
              element.processedImg =  element.img;
              this.reviews.push(element);
          });
      });
  }
  
}
