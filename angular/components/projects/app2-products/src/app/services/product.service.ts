import { Injectable } from "@angular/core";
import { Observable, map, of, tap } from "rxjs";

@Injectable({providedIn : 'root'})
export class ProductService {

    search(form:any):Observable<any[]> {
        return of(PRODUCTS)
    }

    findById(id:any):Observable<any> {
        return of(PRODUCTS.filter(p => p.id == id).pop()).pipe(
            map(item => {
                let productWithFeature = {
                    ... item, 
                    features : {
                        Size : "200g",
                        Color : "Green",
                        Country : "Thailand"
                    }, 
                    images : [
                        'product1.jpg',
                        'product2.jpg',
                        'product3.jpg',
                        'product4.jpg'
                    ]
                }
                return productWithFeature
            }),
            tap(data => console.log(data))
        )
    }
}

const PRODUCTS = [
    {
        id: 1,
        name: "Product 1",
        price: 1000,
        image: "product1.jpg",
        category: [
            {
                id: 1,
                name: "Foods"
            },
            {
                id: 2,
                name: "Fruits"
            },
            {
                id: 3,
                name: "Organic"
            }
        ],
        description: "This is organic product, and made from Pyin Oo Lwin."
    }, 
    {
        id: 2,
        name: "Product 2",
        price: 2000,
        image: "product2.jpg",
        category: [
            {
                id: 1,
                name: "Foods"
            }
        ],
        description: "This is organic product, and made from Pyin Oo Lwin."
    }, 
    {
        id: 3,
        name: "Product 3",
        price: 1000,
        image: "product3.jpg",
        category: [
            {
                id: 1,
                name: "Foods"
            }
        ],
        description: "This is organic product, and made from Pyin Oo Lwin."
    }, 
    {
        id: 4,
        name: "Product 4",
        price: 1000,
        image: "product4.jpg",
        category: [
            {
                id: 1,
                name: "Foods"
            }
        ],
        description: "This is organic product, and made from Pyin Oo Lwin."
    }, 
]