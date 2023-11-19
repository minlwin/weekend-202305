import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-category-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './category-details.component.html',
  styles: ``
})
export class CategoryDetailsComponent {
  @Input()
  item:any

  @Output()
  editEvent = new EventEmitter

  edit() {
    this.editEvent.emit(this.item)
  }
}
