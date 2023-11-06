import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { AsignaturaRoutingModule } from './asignatura-routing.module';
import { AsignaturaComponent } from './page/asignatura/asignatura.component';
import { NewasignaturaComponent } from './page/newasignatura/newasignatura.component';



@NgModule({
  declarations: [
    AsignaturaComponent,
    NewasignaturaComponent
  ],
  imports: [
    MatIconModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    CommonModule,
    AsignaturaRoutingModule,
  ]
})
export class AsignaturaModule { }
