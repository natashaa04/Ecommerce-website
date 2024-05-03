import { Component } from '@angular/core';
import { CustomerService } from '../../services/customerService/customer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})

export class CartComponent {
  cartItems: any[][];
  order: any;
  
  constructor(
      private customerService: CustomerService,
      private snackbar: MatSnackBar,
      private fb: FormBuilder,
      public dialog: MatDialog,
  ) {
      this.cartItems = [];
      this.order = {};
  }


  getCart() {
    this.cartItems = [];
    this.customerService.getCartByUserId().subscribe({
     next :(res) => {
        this.order = res.order;
        res.cartItems.forEach(element => {
            element.processedImg = element.returnedImg;
            this.cartItems.push(element);
        });
    },
    error:(err)=>{

    }
  }
    );
}

  
}
