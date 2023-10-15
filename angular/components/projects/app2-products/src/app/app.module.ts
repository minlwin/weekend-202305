import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductsComponent } from './products/products.component';
import { CategoriesComponent } from './categories/categories.component';
import { ProductSearchComponent } from './products/product-search/product-search.component';
import { ProductListItemComponent } from './products/product-list-item/product-list-item.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ImagesPipe } from './utils/images.pipe';
import { ProductEditComponent } from './products/product-edit/product-edit.component';
import { ProductDetailsComponent } from './products/product-details/product-details.component';
import { CategoryEditComponent } from './categories/category-edit/category-edit.component';
import { HttpClientModule } from '@angular/common/http';
import { CategoryListItemComponent } from './categories/category-list-item/category-list-item.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    CategoriesComponent,
    ProductSearchComponent,
    ProductListItemComponent,
    ImagesPipe,
    ProductEditComponent,
    ProductDetailsComponent,
    CategoryEditComponent,
    CategoryListItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
