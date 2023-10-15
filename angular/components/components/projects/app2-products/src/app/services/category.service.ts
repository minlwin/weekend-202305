import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";

const API = "http://localhost:8080/categories"

@Injectable({providedIn: 'root'})
export class CategoryService {

    constructor(private http:HttpClient) {}

    getAll():Observable<any[]> {
        return this.http.get<any[]>(API)
    }

    save(form:any):Observable<any> {
        if(form.id == 0) {
            return this.http.post<any>(API, form)
        }   

        return this.http.put<any>(`${API}/${form.id}`, form)
    }
}