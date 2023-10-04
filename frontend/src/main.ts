import { bootstrapApplication } from '@angular/platform-browser';
import { Routes, provideRouter, withRouterConfig } from '@angular/router';
import AppComponent from './app/app.component';
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideHttpClient } from '@angular/common/http';

const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./app/login/login.component'),
  },
  {
    path: 'users',
    loadComponent: () => import('./app/users/users.component'),
  },
  {
    path: 'draft',
    loadComponent: () => import('./app/draft/draft.component'),
  },
  {
    path: 'incoming',
    loadComponent: () => import('./app/incoming/incoming.component'),
  },
  {
    path: 'outgoing',
    loadComponent: () => import('./app/outgoing/outgoing.component'),
  },
  {
    path: 'for-approve',
    loadComponent: () => import('./app/for-approve/for-approve.component'),
  },
];

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(),
    provideAnimations(),
    provideRouter(
      routes,
      withRouterConfig({
        onSameUrlNavigation: 'reload',
      }),
    ),
  ],
}).catch(err => console.error(err));
