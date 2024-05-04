import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from '../../services/customerService/customer.service';
import { CurrencyPipe, NgClass, NgFor, NgIf } from '@angular/common';
import { AngularMaterialModule } from '../../AngularMaterialModule';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [AngularMaterialModule, FormsModule,
    ReactiveFormsModule,NgIf,CurrencyPipe,NgClass,NgFor],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.scss'
})
export class CustomerComponent {
  products:any[]=[];
  searchProductForm:FormGroup;
  
  constructor(private customerService :CustomerService,
    private fb:FormBuilder,
    private snackBar:MatSnackBar
    ,
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
      this.customerService.getAllProducts().subscribe({next:(res:any) => {
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
      this.customerService.getAllProductsByName(title).subscribe({
        next:(res:any) => {
  
        res.forEach(element => {
          element.processedImg = element.img;
          this.products.push(element);
        });
        console.log(this.products);
    },
    error:(err:any)=>{}
    
  });
     
  }

  addToCart(id: any) {
    this.customerService.addToCart(id).subscribe(res => {
      this.snackBar.open("Product added to cart successfully", "Close", { duration: 5000 });
    });
  }
  
  }
  
  
  


