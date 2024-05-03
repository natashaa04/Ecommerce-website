import { Component } from '@angular/core';
import { AdminService } from '../../../services/adminService/admin.service';
import { NgIf } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { MatDateRangePicker, MatDatepicker } from '@angular/material/datepicker';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-coupon',
  standalone: true,
  imports: [AngularMaterialModule, FormsModule,
    ReactiveFormsModule,NgIf,MatDatepicker,DatePipe],
  templateUrl: './coupon.component.html',
  styleUrl: './coupon.component.scss'
})
export class CouponComponent {

  coupons:any;

  constructor(private adminService:AdminService){}

  ngOnInit(){
    this.getCoupons();
  }

  getCoupons(){
     this.adminService.getCoupons().subscribe({
      next:(res)=>{
        this.coupons=res;
      },
      error:(err)=>{
        console.log('err while fetching coupons',err);
      }
     })
  }
}
