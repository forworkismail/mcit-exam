import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'app/common/services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
})
export default class LoginComponent {
  form: FormGroup = new FormGroup({});
  private authService = inject(AuthService);

  constructor(private fb: FormBuilder, private router: Router) {
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  login() {
    const val = this.form.value;
    if (val.username && val.password) {
      this.authService.login(val.username, val.password).subscribe(
        (response: any) => {
          console.log(response);
          if (response == false) {
          } else {
            this.authService.addToLocalStorage('role', response);
            this.router.navigateByUrl('/users');
          }
        },
        error => {
          console.log(error);
        },
      );
    }
  }
}
