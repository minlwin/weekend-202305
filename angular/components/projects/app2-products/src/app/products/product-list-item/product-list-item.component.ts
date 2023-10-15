import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-product-list-item',
  templateUrl: './product-list-item.component.html',
  styles: [
  ]
})
export class ProductListItemComponent {

  @Input()
  product:any
}
