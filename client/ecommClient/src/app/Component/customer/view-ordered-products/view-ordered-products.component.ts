import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../../services/customerService/customer.service';
import { CurrencyPipe, DatePipe, NgFor, NgIf } from '@angular/common';
import { AngularMaterialModule } from '../../../AngularMaterialModule';

@Component({
  selector: 'app-view-ordered-products',
  standalone: true,
  imports: [AngularMaterialModule,NgIf,NgFor,DatePipe,CurrencyPipe],
  templateUrl: './view-ordered-products.component.html',
  styleUrl: './view-ordered-products.component.scss'
})
export class ViewOrderedProductsComponent {
  orderId: any = this.activatedroute.snapshot.params['orderId'];
  orderedProductDetailsList = [];
  totalAmount: any;
  
  constructor(private activatedroute: ActivatedRoute, private customerService: CustomerService) {}
  
  ngOnInit() {
    this.getOrderedProductsDetailsByOrderId();
  }
  
  getOrderedProductsDetailsByOrderId() {
    this.customerService.getOrderedProducts(this.orderId).subscribe(res => {
      res.productDtoList.forEach(element => {
        element.processedImg =  element.img;
        this.orderedProductDetailsList.push(element);
      });
      this.totalAmount = res.orderAmount;
    });
  }
  
}
