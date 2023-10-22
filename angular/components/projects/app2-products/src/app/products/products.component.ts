import { Component } from '@angular/core';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styles: [
  ]
})
export class ProductsComponent {

  list:any[] = []

  constructor(private service:ProductService) {}

  search(form:any) {
    this.service.search(form).subscribe(result => this.list = result.content)
  }
}
