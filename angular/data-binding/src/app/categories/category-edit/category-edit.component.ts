import { AfterViewInit, Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

declare const bootstrap:any

@Component({
  selector: 'app-category-edit',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './category-edit.component.html',
  styles: ``
})
export class CategoryEditComponent implements AfterViewInit{

  dialog:any

  form:FormGroup

  @Output()
  onSave = new EventEmitter

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      id: 0,
      name: ["", Validators.required]
    })
  }

  ngAfterViewInit(): void {
    this.dialog = new bootstrap.Modal('#categoryEditDialog')
  }

  show() {
    this.dialog?.show()
  }

  hide() {
    this.dialog?.hide()
  }

  save() {
    if(this.form.valid) {
      this.onSave.emit(this.form.value)
    }
  }

  isNewData() {
    return this.form.get('id')?.value == 0
  }
}
