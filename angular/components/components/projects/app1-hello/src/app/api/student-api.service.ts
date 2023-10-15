import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const ENDPOINT = "http://localhost:8080/student"

@Injectable({
  providedIn: 'root'
})
export class StudentApiService {

  constructor(private http:HttpClient) {}

  search() {
    return this.http.get<any[]>(ENDPOINT)
  }

  create(form:any) {
    return this.http.post<any>(ENDPOINT, form)
  }
}
