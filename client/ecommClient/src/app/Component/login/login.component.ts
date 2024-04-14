import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import {Router} from '@angular/router'
import { AngularMaterialModule } from '../../AngularMaterialModule';
import { UserStorageService } from '../../services/storage/user-storage.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [AngularMaterialModule, FormsModule,
    ReactiveFormsModule,NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  providers:[UserStorageService,AuthService]
})
export class LoginComponent {
  loginForm!: FormGroup;
  hidePassword = true;
  
  constructor(
      private formBuilder: FormBuilder,
      private authService: AuthService,
      private snackBar: MatSnackBar,
      private router: Router
  ) {}
  
  ngOnInit(): void {
      this.loginForm = this.formBuilder.group({
          email: [null, [Validators.required, Validators.email]],
          password: [null, [Validators.required]]
      });
  }
  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
}

onSubmit(): void {
  const username = this.loginForm.get('email')?.value;
  const password = this.loginForm.get('password')?.value;

  this.authService.login(username, password)
      .subscribe({
          next: (res: any) => {
            
            console.log('login res',res)
            if(res){
              
              if (UserStorageService.isAdminLoggedIn()) {
                  this.router.navigateByUrl('admin/dashboard');

              } else if (UserStorageService.isCustomerLoggedIn()) {
                  this.router.navigateByUrl('customer/dashboard');
              }
            }
            else {
                this.snackBar.open('Bad credentials', 'ERROR', { duration: 5000 });
            }
          },
          error: (error: any) => {
            
              this.snackBar.open('Bad credentials', 'ERROR', { duration: 5000 });
          }
      });
}


}
