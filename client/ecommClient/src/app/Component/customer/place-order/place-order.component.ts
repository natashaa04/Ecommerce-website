import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from '../../../services/customerService/customer.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-place-order',
  standalone: true,
  imports: [AngularMaterialModule, FormsModule,
    ReactiveFormsModule,NgIf],
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.scss'
})
export class PlaceOrderComponent {

  orderForm!:FormGroup;

  constructor(
    private fb:FormBuilder,
    private snackBar:MatSnackBar,
    private customerService:CustomerService,
    private router:Router,
    public dialog:MatDialog

  ){}

ngOnInit(){
  this.orderForm=this.fb.group({
address:[null,[Validators.required]],
orderDescription:[null],
  })
}

placeOrder(){
  this.customerService.placeOrder(this.orderForm.value).subscribe({
    next:(res)=>{
         this.snackBar.open("Order Placed Sucessfully","Close",{duration:5000});
         this.dialog.closeAll();
         this.router.navigateByUrl("/customer/my-orders");
        
    },error:(err)=>{
         console.log('error while placing order',err);
         this.snackBar.open("something went wron",'ERROR',{duration:5000})
    }
  })
}
closeForm(){
  this.dialog.closeAll();
}
}
