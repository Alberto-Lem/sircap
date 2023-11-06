
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { InscripcionesComponent } from './page/asignacion/inscripciones.component';
import { InscripcionesRoutingModule } from './inscripciones-routing.module';
import { MaterialModule } from '../material/material.module';
import { ListadoinscripcionesComponent } from './page/listadoinscripciones/listadoinscripciones.component';
import { DetailinscripcionesComponent } from './page/detailinscripciones/detailinscripciones.component';
import { ManualinscripcionComponent } from './page/manualinscripcion/manualinscripcion.component';
import { CsvinscripcionComponent } from './page/csvinscripcion/csvinscripcion.component';






@NgModule({
  declarations: [
    InscripcionesComponent,
    ListadoinscripcionesComponent,
    DetailinscripcionesComponent,
    ManualinscripcionComponent,
    CsvinscripcionComponent
  ],
  imports: [
    MatIconModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    CommonModule,
    MatToolbarModule,
    MaterialModule,
    InscripcionesRoutingModule
  ]
})
export class InscripcionesModule { }
