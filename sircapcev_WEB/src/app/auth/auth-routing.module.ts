import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginDialogComponent } from './page/login-dialog/login-dialog.component';
import { RegisterComponent } from './page/register/register.component';
import { RegisterGuard } from '../guards/register.guard';

const routes: Routes = [
  {
    path: '',
    component: LoginDialogComponent
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [RegisterGuard], data: { expectedRoles: ['admin'] },
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
