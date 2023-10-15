import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-category-list-item',
  templateUrl: './category-list-item.component.html',
  styles: [
  ]
})
export class CategoryListItemComponent {

  @Input()
  category:any

  @Output()
  onEdit = new EventEmitter

  edit() {
    this.onEdit.emit(this.category)
  }
}
