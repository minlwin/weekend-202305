import { Component } from '@angular/core';
import { StudentApiService } from '../api/student-api.service';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styles: [
  ]
})
export class StudentListComponent {

  list:any[] = []

  constructor(service:StudentApiService) {
    service.search().subscribe(result => this.list = result)
  }
}
