import { Routes } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { CategoriesComponent } from './categories/categories.component';

export const routes: Routes = [
  {path: "products", component: ProductsComponent},
  {path: "categories", component: CategoriesComponent},
  {path: "", redirectTo: "/products", pathMatch: "full"}
];
