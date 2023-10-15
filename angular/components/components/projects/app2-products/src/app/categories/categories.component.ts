import { Component, ViewChild } from '@angular/core';
import { CategoryEditComponent } from './category-edit/category-edit.component';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styles: [
  ]
})
export class CategoriesComponent {

  @ViewChild(CategoryEditComponent)
  categoryEdit?:CategoryEditComponent

  list:any[] = []

  constructor(private service:CategoryService) {
    this.loadData()
  }

  loadData() {
    this.service.getAll().subscribe(result => this.list = result)
  }

  addNew() {
    this.categoryEdit?.showDialog({id: 0, name: ""})
  }

  edit(data:any) {
    this.categoryEdit?.showDialog(data)    
  }

  save(form:any) {
    this.service.save(form).subscribe(result => {
      this.loadData()
      this.categoryEdit?.hideDialog()
    })
  }
}
