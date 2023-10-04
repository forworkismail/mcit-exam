import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-draft',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './draft.component.html',
})
export default class DraftComponent {
  letters: any[] = []; // This will hold the data for the table
  letterForm: FormGroup = this.initializeForm();
  showForm = false;

  constructor(private fb: FormBuilder) {
    this.letterForm = this.initializeForm();
  }

  ngOnInit(): void {
    // Fetch letters from your service/API when component initializes
    this.fetchLetters();
  }

  fetchLetters(): void {
    // Assuming you have a service that fetches letters
    // this.letterService.getLetters().subscribe(data => {
    //   this.letters = data;
    // });
  }

  initializeForm(): FormGroup {
    return this.fb.group({
      uniqueIdentifier: ['', Validators.required],
      type: ['', Validators.required],
      content: ['', Validators.required],
      createdBy: ['', Validators.required],
      date: ['', Validators.required],
      status: ['', Validators.required],
      responseTo: [''],
    });
  }

  onSubmit(): void {
    // Handle form submission here
    if (this.letterForm.valid) {
      const formData = this.letterForm.value;

      // Send formData to your backend/API for saving or further processing
      // For now, let's just log it
      console.log(formData);

      // If everything goes well, you can reset the form
      this.letterForm.reset();
    } else {
      // Handle form validation errors
      console.error('Form is invalid');
    }
  }
}
