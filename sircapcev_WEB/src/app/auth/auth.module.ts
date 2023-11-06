import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { MaterialModule } from '../material/material.module';
import { AuthRoutingModule } from './auth-routing.module';
import { LoginDialogComponent } from './page/login-dialog/login-dialog.component';
import { RegisterComponent } from './page/register/register.component';
import { RestablecerusuarioComponent } from './page/restablecerusuario/restablecerusuario.component';
import { PerfilDialogComponent } from './page/perfil-dialog/perfil-dialog.component';

@NgModule({
  declarations: [
    LoginDialogComponent,
    RegisterComponent,
    RestablecerusuarioComponent,
    PerfilDialogComponent

  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    MaterialModule
  ]
})
export class AuthModule { }
