import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InscripcionesComponent } from './page/asignacion/inscripciones.component';
import { ListadoinscripcionesComponent } from './page/listadoinscripciones/listadoinscripciones.component';
import { ManualinscripcionComponent } from './page/manualinscripcion/manualinscripcion.component';
import { FichainscripcionComponent } from './page/fichainscripcion/fichainscripcion.component';
import { CsvinscripcionComponent } from './page/csvinscripcion/csvinscripcion.component';

const routes: Routes = [
  {
    path: '',
    component: InscripcionesComponent,
    children: [
      {
        path: 'listadoinscripciones',
        component: ListadoinscripcionesComponent
      },
      {
        path: 'listadoinscripciones/:id',
        component: ManualinscripcionComponent
      },
      {
        path: 'fichainscripcion',
        component: FichainscripcionComponent
      },
      {
        path: 'manualinscripcion',
        component: ManualinscripcionComponent,
        pathMatch: 'full'
      },
      {
        path: 'csvinscripcion',
        component: CsvinscripcionComponent
      },
      {
        path: 'editar/:id',
        component:ManualinscripcionComponent,
      },
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'listadoinscripciones',
      }
    ]
  }
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class InscripcionesRoutingModule { }
