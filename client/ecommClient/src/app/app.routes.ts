import { Routes } from '@angular/router';
import { LoginComponent } from './Component/login/login.component';
import { SignUpComponent } from './Component/sign-up/sign-up.component';
import { AdminComponent } from './Component/admin/admin.component';
import { PostCatagoryComponent } from './Component/admin/post-catagory/post-catagory.component';
import { PostProductComponent } from './Component/admin/post-product/post-product.component';
import { CustomerComponent } from './Component/customer/customer.component';
import { DashboardComponent } from './Component/admin/dashboard/dashboard.component';
import { PostCouponComponent } from './Component/admin/post-coupon/post-coupon.component';
import { CouponComponent } from './Component/admin/coupon/coupon.component';
import { CartComponent } from './Component/customer/cart/cart.component';
import { OrdersComponent } from './Component/admin/orders/orders.component';
import { MyOrdersComponent } from './Component/customer/my-orders/my-orders.component';
import { PostProductFaqComponent } from './Component/admin/post-product-faq/post-product-faq.component';
import { UpdatedProductComponent } from './Component/admin/updated-product/updated-product.component';
import { ViewOrderedProductsComponent } from './Component/customer/view-ordered-products/view-ordered-products.component';
import { ReviewOrderedProductComponent } from './Component/customer/review-ordered-product/review-ordered-product.component';
import { ViewProductDetailComponent } from './Component/customer/view-product-detail/view-product-detail.component';
import { ViewWishListComponent } from './Component/customer/view-wish-list/view-wish-list.component';
import { TrackOrderComponent } from './Component/track-order/track-order.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent } ,
   { path: 'signup', component: SignUpComponent },
   { path: 'admin', component: AdminComponent },
   { path: 'customer', component: CustomerComponent },
   {path:'admin/dashboard', component:DashboardComponent},
  {path:'admin/category', component:PostCatagoryComponent},
  {path:'admin/product', component:PostProductComponent},
 {path:'customer/dashboard', component:CustomerComponent},
 {path:'admin/post-coupon', component:PostCouponComponent},
 {path:'admin/coupons', component:CouponComponent},
 {path:'customer/cart', component:CartComponent},
 {path:'admin/orders', component:OrdersComponent},
 {path:'customer/my_orders', component:MyOrdersComponent},
 {path:'admin/faq/:productId', component:PostProductFaqComponent},
 {path:'admin/product/:productId', component:UpdatedProductComponent},
 {path:'customer/ordered_products/:orderId', component:ViewOrderedProductsComponent},
 {path:'customer/review/:productId', component:ReviewOrderedProductComponent},
 {path:'customer/product/:productId', component:ViewProductDetailComponent},
 {path:'customer/review/:productId', component:ReviewOrderedProductComponent},
 {path:'customer/wishlist', component:ViewWishListComponent},
 {path:'order', component:TrackOrderComponent},

];
