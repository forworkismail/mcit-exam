import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LetterService } from 'app/common/services/letter.service';

@Component({
  selector: 'app-outgoing',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './outgoing.component.html',
})
export default class OutgoingComponent {
  letters: any[] = []; // This will hold the data for the table
  letterService = inject(LetterService);

  ngOnInit(): void {
    this.fetchLetters();
  }

  fetchLetters(): void {
    this.letterService.getLettersByType('OUTGOING').subscribe(data => {
      this.letters = data;
    });
  }
}
