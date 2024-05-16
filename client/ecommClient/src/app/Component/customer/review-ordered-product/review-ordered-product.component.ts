import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute,Router } from '@angular/router';
import { CustomerService } from '../../../services/customerService/customer.service';
import { UserStorageService } from '../../../services/storage/user-storage.service';
import { NgFor, NgIf } from '@angular/common';
import { AngularMaterialModule } from '../../../AngularMaterialModule';

@Component({
  selector: 'app-review-ordered-product',
  standalone: true,
  imports: [AngularMaterialModule, FormsModule,
    ReactiveFormsModule,NgIf,NgFor],
  templateUrl: './review-ordered-product.component.html',
  styleUrl: './review-ordered-product.component.scss'
})
export class ReviewOrderedProductComponent {
  productId: number = this.activatedRoute.snapshot.params['productId'];
  reviewForm!: FormGroup;
  selectedFile: File | null = null;
  imagePreview: string  | null = null;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private customerService: CustomerService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.reviewForm = this.fb.group({
      rating: [null, [Validators.required]],
      description: [null, [Validators.required]],
    });
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
  



  submitForm() {
    const reviewData = {
        img: this.imagePreview,
        productId: this.productId,
        userId: UserStorageService.getUserId(),
        rating: this.reviewForm.get('rating').value,
        description: this.reviewForm.get('description').value
    };

    console.log('review data is', reviewData);
    
    this.customerService.giveReview(reviewData).subscribe({
        next: (res) => {
            this.snackBar.open('Review Posted Successfully!', 'Close', {
                duration: 5000
            });
            this.router.navigateByUrl('/customer/my_orders');
        },
        error: (err) => {
            this.snackBar.open('Something went wrong', 'ERROR', {
                duration: 5000
            });
        }
    });
}

}
