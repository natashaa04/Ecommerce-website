import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../../services/customerService/customer.service';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-my-orders',
  standalone: true,
  imports: [AngularMaterialModule,DatePipe],
  templateUrl: './my-orders.component.html',
  styleUrl: './my-orders.component.scss'
})
export class MyOrdersComponent {
  myOrders: any;

  constructor(private customerService: CustomerService) {}

  ngOnInit() {
    this.getMyOrders();
  }

  getMyOrders() {
    
    this.customerService.getOrdersByUserId().subscribe({
      next: (res) => {
        
        this.myOrders = res;
      },
      error: (err) => {
        console.log('Error while fetching customer orders:', err);
      }
    });
  }
}
