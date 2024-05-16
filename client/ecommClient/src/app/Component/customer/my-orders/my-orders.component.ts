import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../../services/customerService/customer.service';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { RouterLink,Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-my-orders',
  standalone: true,
  imports: [AngularMaterialModule,DatePipe,RouterModule,NgIf,NgFor],
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
