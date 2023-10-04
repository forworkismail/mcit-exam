import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: [``],
  standalone: true,
  imports: [RouterOutlet, RouterLink],
})
export default class AppComponent {
  title = 'MCIT exam';
}
