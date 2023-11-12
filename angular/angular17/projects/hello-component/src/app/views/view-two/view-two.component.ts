import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BaseView } from '../base-view';

@Component({
  selector: 'app-view-two',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './view-two.component.html',
  styles: ``
})
export class ViewTwoComponent extends BaseView{

  constructor() {
    super("View Two")
  }
}
