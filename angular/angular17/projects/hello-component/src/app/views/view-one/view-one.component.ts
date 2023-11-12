import { Component, OnInit } from '@angular/core';
import { BaseView } from '../base-view';

@Component({
  selector: 'app-view-one',
  templateUrl: './view-one.component.html',
  styleUrl: './view-one.component.css'
})
export class ViewOneComponent extends BaseView{

  constructor() {
    super("View One")
  }
}
