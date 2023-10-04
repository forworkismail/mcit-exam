import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-panel-shell',
  standalone: true,
  templateUrl: './panel-shell.component.html',
  imports: [CommonModule, RouterOutlet],
})
export default class PanelShellComponent {}
