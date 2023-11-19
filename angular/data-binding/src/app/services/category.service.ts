import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const URL = "http://localhost:8080/categories"

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http:HttpClient) { }

  findAll() {
    return this.http.get<any[]>(URL)
  }

  save(form:any) {

    if(form.id > 0) {
      return this.http.put<any>(`${URL}/${form.id}`, form)
    }

    return this.http.post<any>(URL, form)
  }
}
