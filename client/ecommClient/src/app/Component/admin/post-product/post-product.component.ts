import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../../services/adminService/admin.service';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { NgFor, NgIf } from '@angular/common';


@Component({
  selector: 'app-post-product',
  standalone: true,
  imports: [AngularMaterialModule, FormsModule,
    ReactiveFormsModule,NgIf,NgFor],
  templateUrl: './post-product.component.html',
  styleUrl: './post-product.component.scss',

})
export class PostProductComponent {
  productForm: FormGroup;
  listofCategories: any = [];
  selectedFile: File | null;
  imagePreview: string | null;
  
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {
    this.productForm = this.fb.group({
      // img: [null, [Validators.required]],
      categoryId: [null, [Validators.required]],
      name: [null, [Validators.required]],
      price: [null, [Validators.required]],
      description: [null, [Validators.required]],
    });
    this.getAllCategories();
    console.log('cateories are',this.listofCategories);
  }

  onFileSelected(event: any): void {
    // console.log('selected file');
    this.selectedFile = event.target.files[0];
    this.previewImage();

  }

  
  
  previewImage(): void {
    const reader = new FileReader();
    reader.onload = () => {
     
      const imageData = reader.result.toString().split(',')[1];
      this.imagePreview = 'data:image/jpeg;base64,' + imageData;
    };
    
    reader.readAsDataURL(this.selectedFile);
  }
  
  

 
  
  getAllCategories(): void {
    this.adminService.getAllCategories().subscribe({
      next:(res)=>{
        console.log('categories res',res);
      this.listofCategories = res;
      },
      error:(err)=>{
        console.log('categories err',err);

      }
    });
  }

addProduct(): void {
    if (this.productForm.valid) {

const formData: FormData = new FormData();
formData.append('name', this.productForm.get('name').value);
formData.append('price', this.productForm.get('price').value);
formData.append('description', this.productForm.get('description').value);
formData.append('categoryId', this.productForm.get('categoryId').value);
formData.append('img', this.imagePreview);
const serializedFormData = {};
formData.forEach((value, key) => {
  serializedFormData[key] = value;
});
      
      this.adminService.addProduct(serializedFormData).subscribe({
        next:(res) => {
        if (res.id != null) {
          this.snackBar.open('Product Added Successfully!', 'Close', { duration: 5000 });
          this.router.navigateByUrl('/admin/dashboard');
        } else {
          this.snackBar.open(res.message, 'ERROR', { duration: 5000 });
        }
      },
        error:(error)=>{

        }
      
      });
    } else {
      for (const i in this.productForm.controls) {
        this.productForm.controls[i].markAsDirty();
        this.productForm.controls[i].updateValueAndValidity();
      }
    }
  }
  

  
  
}
