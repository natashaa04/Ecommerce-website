import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from '../../../services/adminService/admin.service';
import { ActivatedRoute } from '@angular/router';
import {Router} from '@angular/router';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { CommonModule, NgIf } from '@angular/common';
@Component({
  selector: 'app-post-product-faq',
  standalone: true,
  imports: [AngularMaterialModule ,FormsModule,
    ReactiveFormsModule,CommonModule,NgIf],
  templateUrl: './post-product-faq.component.html',
  styleUrl: './post-product-faq.component.scss'
})
export class PostProductFaqComponent {

  productId:number=this.activatedRoute.snapshot.params['productId'];
  FAQForm:FormGroup;

  constructor(private fb:FormBuilder,
    private snackBar:MatSnackBar,
    private adminService:AdminService,
    private activatedRoute:ActivatedRoute,
    private router: Router
    ){}

    ngOnInit(){
      this.FAQForm=this.fb.group({
        question:[null,[Validators.required]],
        answer:[null,[Validators.required]],
      })
    }


postFAQ(){
  this.adminService.postFAQ(this.productId,this.FAQForm.value).subscribe({
    next:(res)=>{
      this.snackBar.open('FAQ Posted Successfully','Close',{duration:5000});
      this.router.navigateByUrl('/admin/dashboard');
    },
    error:(err)=>{
         this.snackBar.open("Something Went Wrong",'Close',{
          duration:5000,
          panelClass:'error-snackbar'
         });
    }

  })
}
}
