import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import {Router, RouterLink, RouterModule} from '@angular/router'
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { NgFor, NgIf } from '@angular/common';
import { AdminService } from '../../../services/adminService/admin.service';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [AngularMaterialModule, FormsModule,
    ReactiveFormsModule,NgIf,RouterLink,NgFor,RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {
products:any[]=[];
searchProductForm:FormGroup;

constructor(private adminService :AdminService,
  private fb:FormBuilder,
  private snackBar:MatSnackBar,
  ){}

  ngOnInit(): void {
    this.getAllProducts();
    this.searchProductForm = this.fb.group({
      title: [null, [Validators.required]]
    });
  }

  onInputChange() {
    const searchTerm = this.searchProductForm.get('title').value;
    if (searchTerm === '') {
       this.getAllProducts(); // Show all products if search bar is empty
    }
  }
  
  
  getAllProducts(): void {
    this.products = [];
    this.adminService.getAllProducts().subscribe({next:(res:any) => {
      res.forEach(element => {
        element.processedImg =  element.img;
        this.products.push(element);
      });
      console.log(this.products);
    },error:(err:any)=>{

    }
    });
  }
  submitForm(): void {
    this.products = [];
    const title = this.searchProductForm.get('title').value;
    this.adminService.getAllProductsByName(title).subscribe({
      next:(res:any) => {

      res.forEach(element => {
        this.products.push(element);
      });
      console.log(this.products);
  },
  error:(err:any)=>{}
  
});
  }
  deleteProduct(productId: any) {

    this.adminService.deleteProduct(productId).subscribe({
      next:(res:any) => {
        console.log('product deleted1');
        
            this.snackBar.open('Product Deleted Successfully!', 'close', {
                duration: 5000
            });
            console.log('product deleted');
            this.getAllProducts();
          
        
        },
      
        error:(err:any)=>{
          console.log('product not deleted',err);
          this.snackBar.open(err.message, 'Close', {
              duration: 5000,
              panelClass: 'error-snackbar'
          });
        }
        });
}
}


