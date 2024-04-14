import { Component, NgModule } from '@angular/core';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { AngularMaterialModule } from '../../AngularMaterialModule';
import { CommonModule, NgIf } from '@angular/common';
import { UserStorageService } from '../../services/storage/user-storage.service';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [AngularMaterialModule ,FormsModule,
    ReactiveFormsModule,CommonModule,NgIf],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss',
  providers:[UserStorageService,AuthService]
})
export class SignUpComponent {
  signupForm!: FormGroup;
  hidePassword = true;
  
  constructor(
      private fb: FormBuilder,
      private snackBar: MatSnackBar,
      private authService: AuthService,
      private router: Router
  ) {}
  
  ngOnInit(): void {
      this.signupForm = this.fb.group({
          name: [null, [Validators.required]],
          email: [null, [Validators.required, Validators.email]],
          password: [null, [Validators.required]],
          confirmPassword: [null, [Validators.required]]
      });
  }
  
  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
}
onSubmit(): void {
  const password = this.signupForm.get('password')?.value;
  const confirmPassword = this.signupForm.get('confirmPassword')?.value;
console.log(password);
  if (password !== confirmPassword) {
    console.log('wrong');
      this.snackBar.open('Passwords do not match.', 'close', { duration: 5000, panelClass: 'error-snackbar' });
      return;
  }

  this.authService.register(this.signupForm.value)
      .subscribe({
          next: (response) => {
            console.log(response);
              this.snackBar.open('Sign up successful!', 'Close', { duration: 5000 });
              this.router.navigateByUrl("/login");
          },
          error: (error) => {
            console.log(error);
              this.snackBar.open('Sign up failed. Please try again.', 'Close', { duration: 5000, panelClass: 'error-snackbar' });
          }
      });
}
}
