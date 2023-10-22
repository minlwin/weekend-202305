import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, map, of, tap } from "rxjs";
import { Page } from "./model/page.type";

const API = "http://localhost:8080"

@Injectable({providedIn : 'root'})
export class ProductService {

    constructor(private http:HttpClient) {}

    search(form:any):Observable<Page> {
        return this.http.get<Page>(`${API}/products`, {params: form})
    }

    findById(id:any):Observable<any> {
        return this.http.get<any>(`${API}/products/${id}`)
    }
}
