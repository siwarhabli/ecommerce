import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { DashboardComponent } from './admin/components/dashboard/dashboard.component';
import { PostCategoryComponent } from './admin/components/post-category/post-category.component';
import { PostProductComponent } from './admin/components/post-product/post-product.component';
import { LoginComponent } from './login/login.component';


const routes: Routes = [
  { path: '', component: AdminComponent }, // page d'accueil admin
  { path: 'login', component: LoginComponent },

  {
    path: 'admin',
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'category', component: PostCategoryComponent },
      { path: 'product', component: PostProductComponent },
    ]
  },
  // optionnel : redirect si aucune route ne matche
  { path: '**', redirectTo: '' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
