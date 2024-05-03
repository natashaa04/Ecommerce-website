import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AdminService } from '../../../services/adminService/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { NgIf } from '@angular/common';


@Component({
  selector: 'app-post-coupon',
  standalone: true,
  imports: [AngularMaterialModule, FormsModule,
    ReactiveFormsModule,NgIf],
  templateUrl: './post-coupon.component.html',
  styleUrl: './post-coupon.component.scss'
})
export class PostCouponComponent {
  couponForm!: FormGroup;

  constructor(
      private fb: FormBuilder,
      private router: Router,
      private snackBar: MatSnackBar,
      private adminService: AdminService
  ) {}
  
  ngOnInit() {
      this.couponForm = this.fb.group({
          name: [null, [Validators.required]],
          code: [null, [Validators.required]],
          discount: [null, [Validators.required]],
          expirationDate: [null, [Validators.required]],
      });
  }


  addCoupon() {
    if (this.couponForm.valid) {
        this.adminService.addCoupon(this.couponForm.value).subscribe({
          next:(res) => {
            if (res.id != null) {
                this.snackBar.open("Coupon Posted Successfully!", 'Close', {
                    duration: 5000
                });
                this.router.navigateByUrl('/admin/dashboard');
            }
          },
             error:(err)=> {
                this.snackBar.open(err.message, 'close', {
                    duration: 5000,
                    panelClass: 'error-snackbar'
                });
            }
          
        });
    } else {
        this.couponForm.markAllAsTouched();
    }
}

  
}
