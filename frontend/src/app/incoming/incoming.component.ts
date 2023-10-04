import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LetterService } from 'app/common/services/letter.service';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-incoming',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './incoming.component.html',
})
export default class IncomingComponent {
  letters: any[] = []; // This will hold the data for the table
  letterService = inject(LetterService);

  ngOnInit(): void {
    // Fetch letters from your service/API when component initializes
    this.fetchLetters();
  }

  fetchLetters(): void {
    this.letterService.getLettersByType('INCOMING').subscribe(data => {
      this.letters = data;
    });
  }
}
