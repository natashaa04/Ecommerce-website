import { Component } from '@angular/core';
import { AdminService } from '../../../services/adminService/admin.service';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { CurrencyPipe, DatePipe } from '@angular/common';
import { OrderByStatusComponent } from './order-by-status/order-by-status.component';
@Component({
  selector: 'app-analytics',
  standalone: true,
  imports: [AngularMaterialModule,DatePipe,CurrencyPipe,OrderByStatusComponent],
  templateUrl: './analytics.component.html',
  styleUrl: './analytics.component.scss'
})
export class AnalyticsComponent {

   data:any;

   constructor(private adminService:AdminService){}

   ngOnInit(){
    this.adminService.getAnalytics().subscribe({
      next:(res)=>{
        this.data=res;
        console.log('res of analytics',res);
      },
      error:(err)=>{
        console.log('error while  getching analtyics');
      }
    })
   }

}
