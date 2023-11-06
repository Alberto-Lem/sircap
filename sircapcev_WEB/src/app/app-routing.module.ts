import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './welcome/pages/home/home.component';
import { PersonaGuard } from './guards/persona.guard';
import { PlanesGuard } from './guards/planes.guard';
import { ErrorPageComponent } from './shared/error-page/error-page.component';
import { DashboardComponent } from './welcome/pages/dashboard/dashboard.component';
import { DashboardGuard } from './guards/dashboard.guard';
import { InscripcionesGuard } from './guards/inscripciones.guard';
import { CertificadosGuard } from './guards/certificados.guard';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule),
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [DashboardGuard], data: { expectedRoles: ['admin', 'user'] },
  },
  {
    path: 'personas',
    loadChildren: () => import('./persona/persona.module').then((m) => m.PersonaModule),
    canActivate: [PersonaGuard], data: { expectedRoles: ['admin', 'user'] },
  },

  {
    path: 'planes',
    loadChildren: () => import('./planes/planes.module').then((m) => m.PlanesModule),
    canActivate: [PlanesGuard], data: { expectedRoles: ['admin', 'user'] },
  },
  {
    path: 'inscripciones',
    loadChildren: () => import('./inscripcion/inscripciones.module').then((m) => m.InscripcionesModule),
    canActivate: [InscripcionesGuard], data: {expectedRoles: ['admin', 'user']},
  },

  
  {
    path: 'certificados',
    loadChildren: () => import('./constancia/constancia.module').then((m) => m.ConstanciaModule),
    canActivate: [CertificadosGuard], data: {expectedRoles: ['admin', 'user']},
  },

  {
    path: 'asignatura',
    loadChildren: () => import('./asignatura/asignatura.module').then((m) => m.AsignaturaModule),
  },
  {
    path: 'ciclo',
    loadChildren: () => import('./ciclo/ciclo.module').then((m) => m.CicloModule),
  },

  {
    path: 'error',
    component: ErrorPageComponent,
  },
  { path: '**', redirectTo: '/error' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    initialNavigation: 'enabledBlocking'
})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
