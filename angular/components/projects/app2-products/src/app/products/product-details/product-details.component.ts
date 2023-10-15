import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../services/product.service';

@Component({
  templateUrl: './product-details.component.html',
  styles: [
  ]
})
export class ProductDetailsComponent implements OnInit{

  product:any
  coverImage?:string

  constructor(private route:ActivatedRoute, private service:ProductService) {}
  
  ngOnInit(): void {
    this.route.paramMap.subscribe(map => {
      let id = map.get('id')
      this.service.findById(id).subscribe(result => {
        this.product = result
        this.coverImage = this.product.image
      })
    })
  }

  get features() {
    if(this.product?.features) {
      return Object.entries(this.product.features)
    }

    return []
  }

}
