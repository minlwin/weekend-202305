import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms'
import { StudentApiService } from '../api/student-api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-edit',
  templateUrl: './student-edit.component.html',
  styles: [
  ]
})
export class StudentEditComponent {

  form:FormGroup

  constructor(builder:FormBuilder, private service:StudentApiService, private router:Router) {
    this.form = builder.group({
      name: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    })
  }

  save() {
    if(this.form.valid) {
      this.service.create(this.form.value).subscribe(data => {
        this.router.navigate(['/students'])
      })
    }
  }
}
