import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoryService } from '../../services/category.service';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-edit',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './product-edit.component.html',
  styles: ``
})
export class ProductEditComponent {

  categories:any[] = []

  form:FormGroup

  constructor(
    private builder:FormBuilder,
    private router:Router,
    private service:ProductService,
    categoryService:CategoryService) {

    this.form = builder.group({
      name: ["", Validators.required],
      categories: builder.array([]),
      status: ["", Validators.required],
      price: "0",
      description: ["", Validators.required],
      features: builder.array([])
    })

    categoryService.findAll().subscribe(result => {
      this.categories = result
    })
  }

  get categoriesFormArray() {
    return this.form.get('categories') as FormArray
  }

  get featureFormArray() {
    return this.form.get('features') as FormArray
  }

  addNewFeature() {
    this.featureFormArray.push(this.builder.group({
      "name" : ["", Validators.required],
      "value" : ["", Validators.required]
    }))
  }

  deleteFeature(index:number) {
    this.featureFormArray.removeAt(index)
  }

  checkCategory(target:any, category:any) {
    if(target.checked) {
      this.categoriesFormArray.push(this.builder.control(category.id))
    } else {
      let array = this.categoriesFormArray.value as any[]
      let index = array.findIndex(a => a == category.id)
      this.categoriesFormArray.removeAt(index)
    }
  }

  save() {
    if(this.form.valid) {

      let {features, ... others} = this.form.value
      let form = {... others, features : {}}

      for(let feature of features) {
        form.features[feature.name] = feature.value
      }

      this.service.create(form).subscribe(result => {
        if(result.id) {
          // Navigate to
          this.router.navigate(['/product-details'], {queryParams: {id: result.id}})
        }
      })
    }
  }
}
