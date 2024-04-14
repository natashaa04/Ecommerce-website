import { Routes } from '@angular/router';
import { LoginComponent } from './Component/login/login.component';
import { SignUpComponent } from './Component/sign-up/sign-up.component';
import { AdminComponent } from './Component/admin/admin.component';
import { CustomerComponent } from './Component/customer/customer.component';
export const routes: Routes = [
  { path: 'login', component: LoginComponent } ,
  { path: 'signup', component: SignUpComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'customer', component: CustomerComponent },
  {path:'admin/dashboard', component:AdminComponent},
  {path:'customer/dashboard', component:CustomerComponent},
];
