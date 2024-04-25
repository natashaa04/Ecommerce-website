import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AngularMaterialModule } from './AngularMaterialModule';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { SignUpComponent } from './Component/sign-up/sign-up.component';
import { LoginComponent } from './Component/login/login.component';
import {Router} from '@angular/router';
import { UserStorageService } from './services/storage/user-storage.service';
import { AdminService } from './services/adminService/admin.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    LoginComponent,
    SignUpComponent,
    RouterOutlet,
    AngularMaterialModule,
    RouterLink,
    RouterLinkActive,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatCardModule,
    CommonModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  providers:[UserStorageService,AdminService]
})
export class AppComponent {
  title = 'ecommClient';

  isCustomerLoggedIn: boolean;
isAdminLoggedIn: boolean;
Token:String;
User:String;


constructor(private router: Router) {}

ngOnInit(): void {
    this.router.events.subscribe(event => {
        this.isCustomerLoggedIn = UserStorageService.isCustomerLoggedIn();
        this.isAdminLoggedIn = UserStorageService.isAdminLoggedIn();
        this.Token= UserStorageService.getToken();
        this.User=UserStorageService.getUser();
        console.log('token token',this.Token);
       AdminService.getToken(this.Token)

    });
}

logout(): void {
    UserStorageService.signOut();
    this.router.navigateByUrl("login");
}

} 
