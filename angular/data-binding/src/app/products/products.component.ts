import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CategoryService } from '../services/category.service';
import { ProductService } from '../services/product.service';
import { RouterLink } from '@angular/router';
import { resourceUsage } from 'process';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './products.component.html',
  styles: ``
})
export class ProductsComponent {

  list:any[] = []

  categories:any[] = []

  form:FormGroup

  constructor(
    builder:FormBuilder,
    categoryService:CategoryService,
    private productService:ProductService) {
    this.form = builder.group({
      category: 0,
      status: "",
      keyword: ""
    })

    categoryService.findAll().subscribe(result => {
      this.categories = result
    })

    productService.search().subscribe(result => {
      this.list = result.content
    })
  }

}
