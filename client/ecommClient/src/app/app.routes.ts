import { Routes } from '@angular/router';
import { LoginComponent } from './Component/login/login.component';
import { SignUpComponent } from './Component/sign-up/sign-up.component';
import { AdminComponent } from './Component/admin/admin.component';
import { PostCatagoryComponent } from './Component/admin/post-catagory/post-catagory.component';
import { PostProductComponent } from './Component/admin/post-product/post-product.component';
import { CustomerComponent } from './Component/customer/customer.component';
import { DashboardComponent } from './Component/admin/dashboard/dashboard.component';
export const routes: Routes = [
  { path: 'login', component: LoginComponent } ,
   { path: 'signup', component: SignUpComponent },
   { path: 'admin', component: AdminComponent },
   { path: 'customer', component: CustomerComponent },
   {path:'admin/dashboard', component:DashboardComponent},
  {path:'admin/category', component:PostCatagoryComponent},
  {path:'admin/product', component:PostProductComponent},
 {path:'customer/dashboard', component:CustomerComponent},
];
