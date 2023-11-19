import { Component, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoryEditComponent } from './category-edit/category-edit.component';
import { CategoryDetailsComponent } from './category-details/category-details.component';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-categories',
  standalone: true,
  imports: [
    CommonModule,
    CategoryEditComponent,
    CategoryDetailsComponent
  ],
  templateUrl: './categories.component.html',
  styles: ``
})
export class CategoriesComponent {

  @ViewChild(CategoryEditComponent)
  editDialog?:CategoryEditComponent

  list:any[] = []

  constructor(
    private service:CategoryService
  ) {
    service.findAll().subscribe(result => {
      this.list = result
    })
  }

  addNew() {
    this.editDialog?.form.patchValue({
      id: 0,
      name: ""
    })
    this.editDialog?.show()
  }

  edit(c:any) {
    this.editDialog?.form.patchValue(c)
    this.editDialog?.show()
  }

  saveCategory(c:any) {
    this.service.save(c).subscribe(() => {
      this.service.findAll().subscribe(result => {
        this.list = result
      })
    })
    this.editDialog?.hide()
  }
}
