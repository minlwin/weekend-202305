import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-details.component.html',
  styles: ``
})
export class ProductDetailsComponent {

  data:any = {}

  get featureNames():string[] {
    return Object.keys(this.data.features) || []
  }

  constructor(route:ActivatedRoute, service:ProductService) {
    route.queryParams.subscribe(params => {
      if(params['id']) {
        service.findById(params['id']).subscribe(result => {
          this.data = result
        })
      }
    })
  }
}
