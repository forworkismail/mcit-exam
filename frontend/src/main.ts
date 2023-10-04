import { bootstrapApplication } from '@angular/platform-browser';
import { Routes, provideRouter, withRouterConfig } from '@angular/router';
import AppComponent from './app/app.component';
import { provideAnimations } from '@angular/platform-browser/animations';
import { provideHttpClient } from '@angular/common/http';
import { AuthGuard } from 'app/guards/auth.guard';

const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./app/login/login.component'),
  },
  {
    path: 'users',
    loadComponent: () => import('./app/panel/users/users.component'),
    canActivate: [AuthGuard],
  },
  {
    path: 'draft',
    loadComponent: () => import('./app/draft/draft.component'),
    canActivate: [AuthGuard],
  },
  {
    path: 'incoming',
    loadComponent: () => import('./app/incoming/incoming.component'),
    canActivate: [AuthGuard],
  },
  {
    path: 'outgoing',
    loadComponent: () => import('./app/outgoing/outgoing.component'),
    canActivate: [AuthGuard],
  },
  {
    path: 'for-approve',
    loadComponent: () => import('./app/for-approve/for-approve.component'),
    canActivate: [AuthGuard],
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
