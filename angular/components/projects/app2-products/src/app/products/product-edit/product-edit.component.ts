import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styles: [
  ]
})
export class ProductEditComponent {

  form:FormGroup

  constructor(route:ActivatedRoute, builder:FormBuilder) {

    this.form = builder.group({
      id: 0,
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: [0, Validators.min(1000)],
      categories: builder.array([]),
      features: builder.array([])
    })

    route.queryParamMap.subscribe(map => {
      if(map.get("id")) {

      }
    })
  }
}
