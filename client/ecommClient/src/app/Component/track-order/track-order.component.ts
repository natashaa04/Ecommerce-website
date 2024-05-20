import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { AngularMaterialModule } from '../../AngularMaterialModule';
import { CommonModule, CurrencyPipe, DatePipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-track-order',
  standalone: true,
  imports: [AngularMaterialModule,
    NgIf,
    NgFor,
    DatePipe,
    CurrencyPipe,FormsModule,
    ReactiveFormsModule,
    CommonModule],
  templateUrl: './track-order.component.html',
  styleUrl: './track-order.component.scss'
})
export class TrackOrderComponent {
  
  searchOrderForm!:FormGroup;
  order:any;

  constructor(private fb:FormBuilder,private authService:AuthService){}

  ngOnInit(){
    this.searchOrderForm=this.fb.group({
      trackingId:[null,[Validators.required]]
    })
  }

  submitForm(){
    this.authService.getOrderByTrackingId(this.searchOrderForm.get('trackingId').value).subscribe(
      {
        next:(res)=>{
         this.order=res;
        },
        error:(err)=>{
          console.log('error while fetching order by trackingggId');
        }
      }
    )
  }
}
