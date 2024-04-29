import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../../services/adminService/admin.service';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { CommonModule, NgIf } from '@angular/common';

@Component({
  selector: 'app-post-catagory',
  standalone: true,
  imports: [AngularMaterialModule ,FormsModule,
    ReactiveFormsModule,CommonModule,NgIf],
  templateUrl: './post-catagory.component.html',
  styleUrl: './post-catagory.component.scss',
  providers:[AdminService],
})
export class PostCatagoryComponent {
  categoryForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminservice: AdminService
    
  ) { }
  
  ngOnInit(): void {
    this.categoryForm = this.fb.group({
      name: [null, [Validators.required]],
      description: [null, [Validators.required]]
    });
  }
  
  addCategory(): void {

    if (this.categoryForm.valid) {

      this.adminservice.addCategory(this.categoryForm.value).subscribe({

        next:(res) => {
        if (res.id != null) {
          this.snackBar.open("Category Posted Successfully!", 'Close', { duration: 5000 });
          this.router.navigateByUrl('/admin/dashboard');
        } else {
          this.snackBar.open(res.message, 'Close', { duration: 5000, panelClass: "error-snackbar" });
        }
      },
      
        error:(err)=>{

        }

        
      });
    } else {
      this.categoryForm.markAllAsTouched();
    }
  
  }
  
  
}
