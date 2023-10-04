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
  responseChain: any[] = []; // This will hold the response chain for the selected letter

  ngOnInit(): void {
    // Fetch letters from your service/API when component initializes
    this.fetchLetters();
  }

  fetchLetters(): void {
    this.letterService.getLettersByType('INCOMING').subscribe(data => {
      this.letters = data;
    });
  }

  showResponseChain(letterId: number) {
    // Call your service here to fetch the response chain for the given letter ID
    // You'd ideally update a local variable (e.g., responseChain) that you can then display in a modal or another part of your UI
    this.letterService.getResponseChain(letterId).subscribe(data => {
      this.responseChain = data;
      // You might also want to open a modal or some UI element to show this chain
    });
  }
}
