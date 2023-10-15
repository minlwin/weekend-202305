import { AfterViewInit, Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

declare const bootstrap:any

@Component({
  selector: 'app-category-edit',
  templateUrl: './category-edit.component.html',
  styles: [
  ]
})
export class CategoryEditComponent implements AfterViewInit{

  modalDialog:any
  form:FormGroup

  @Output()
  onSave = new EventEmitter

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      id: 0,
      name: ['', Validators.required]
    })
  }

  ngAfterViewInit(): void {
      this.modalDialog = new bootstrap.Modal('#categoryEditDialog')
  }

  save() {
    if(this.form.valid) {
      this.onSave.emit(this.form.value)
    }
  }

  showDialog(data:any) {
    this.form.patchValue(data)
    this.modalDialog?.show()
  } 

  hideDialog() {
    this.modalDialog?.hide()
  }
}
