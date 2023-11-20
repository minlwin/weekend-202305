import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const URL = "http://localhost:8080/products"

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:HttpClient) { }

  search() {
    return this.http.get<any>(URL)
  }

  create(form:any) {
    return this.http.post<any>(URL, form)
  }

  findById(id:any) {
    return this.http.get<any>(`${URL}/${id}`)
  }
}
