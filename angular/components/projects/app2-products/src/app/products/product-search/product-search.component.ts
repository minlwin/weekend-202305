import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styles: [
  ]
})
export class ProductSearchComponent {

  form:FormGroup

  @Output()
  searchEventListener = new EventEmitter

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      category: '',
      keyword: '',
      priceFrom: '',
      priceTo: '',
      status: ''
    })
  }

  search() {
    this.searchEventListener.emit(this.form.value)
  }
}
