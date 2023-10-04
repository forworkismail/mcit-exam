import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from './common/services/auth.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: [``],
  standalone: true,
  imports: [RouterOutlet, RouterLink, NgIf],
})
export default class AppComponent {
  title = 'MCIT exam';
  private router = inject(Router);
  role: string | null = '';

  constructor() {}

  ngOnInit() {
    this.role = localStorage.getItem('role');
    if (this.role == null) {
      this.router.navigate(['/login']);
    }
  }
}
