import { Routes } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { CategoriesComponent } from './categories/categories.component';
import { ProductEditComponent } from './products/product-edit/product-edit.component';
import { ProductDetailsComponent } from './products/product-details/product-details.component';

export const routes: Routes = [
  {path: "products", component: ProductsComponent},
  {path: "product-edit", component: ProductEditComponent},
  {path: "product-details", component: ProductDetailsComponent},
  {path: "categories", component: CategoriesComponent},
  {path: "", redirectTo: "/products", pathMatch: "full"}
];
