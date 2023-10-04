import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LetterService } from 'app/common/services/letter.service';

@Component({
  selector: 'app-for-approve',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './for-approve.component.html',
})
export default class ForApproveComponent {
  letters: any[] = []; // This will hold the data for the table
  letterService = inject(LetterService);

  ngOnInit(): void {
    this.fetchLetters();
  }

  fetchLetters(): void {
    this.letterService.getLettersByStatus('DRAFT').subscribe(data => {
      this.letters = data;
    });
  }

  approveLetter(letterId: number): void {
    this.letterService.approveLetter(letterId).subscribe(
      response => {
        console.log(response);
        // Handle successful approval response, perhaps show a toast/notification
        // Also, you might want to refresh the letters list to reflect the new status
        this.fetchLetters();
      },
      error => {
        // Handle errors here, maybe display an error message or alert
        console.error('Failed to approve the letter:', error);
      },
    );
  }
}
