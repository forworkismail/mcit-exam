import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, NavigationEnd, RouterOutlet } from '@angular/router';
import { Subscription, filter } from 'rxjs';
import { TextTitleComponent } from '../common/components/text-title/text-title.component';
import { OverlayComponent } from '../common/components/overlay/overlay.component';

@Component({
  selector: 'app-panel-shell',
  standalone: true,
  templateUrl: './panel-shell.component.html',
  imports: [CommonModule, TextTitleComponent, RouterOutlet, OverlayComponent],
})
export default class PanelShellComponent {
  activeRoute = '';
  routerSubscription = new Subscription();
  toggleSidebar = false;
  count = signal(0);

  private router = inject(Router);

  ngOnInit() {
    this.getActiveRouteSegment();
    this.routerSubscription = this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe(() => {
      this.getActiveRouteSegment();
    });
    console.log('The count is: ' + this.count());
  }
  getActiveRouteSegment(): void {
    const urlSegments = this.router.url.split('/');
    this.activeRoute = urlSegments[1];
  }
  onToggleSidebar() {
    this.toggleSidebar = !this.toggleSidebar;
  }
  increase() {
    this.count.update(value => value + 1);
  }
}
