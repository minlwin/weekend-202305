import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { ViewsModule } from './views/views.module';
import { ViewTwoComponent } from './views/view-two/view-two.component';
import { BaseView } from './views/base-view';
import { QuoteDirective } from './views/quote.directive';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    ViewsModule,
    ViewTwoComponent,
    QuoteDirective
  ],
  templateUrl: './app.component.html',
  styles: [],
})
export class AppComponent extends BaseView{
  title = 'hello-component';

  clickCount = 0

  constructor() {
    super("App Component")
  }

  click() {
    this.clickCount ++
  }
}
