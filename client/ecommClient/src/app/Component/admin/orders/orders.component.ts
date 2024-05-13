import { Component } from '@angular/core';
import { AdminService } from '../../../services/adminService/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DatePipe } from '@angular/common';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [AngularMaterialModule,DatePipe],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.scss'
})
export class OrdersComponent {
    
  orders:any;

  constructor(private adminService:AdminService,private snackBar:MatSnackBar){}

  ngOnInit(){
    this.getPlacedOrders();
  }

  getPlacedOrders(){
    this.adminService.getPlacedOrders().subscribe({
      next:(res)=>{
        this.orders=res;
      },
      error:(err)=>{
        console.log('error while getting orders');
      }
    })
  }

  changeOrderStatus(orderId:number,status:String){

    this.adminService.changeOrderStatus(orderId,status).subscribe({
      next:(res)=>{
      
        this.snackBar.open("Order status changed successfully","Close",{duration:5000})
        this.getPlacedOrders();
      },
      error:(err)=>{
           this.snackBar.open("something went wrong","Close",{duration:5000})
      }
    })

  }
}
