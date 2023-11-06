import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AsignaturaComponent } from './page/asignatura/asignatura.component';

const routes: Routes = [
  {
    path: '',
    children: [
      
      {
        path: 'asignatura',
        component: AsignaturaComponent
      },
      {
        path: 'asignatura/:id',
        component: AsignaturaComponent
      },
      {
        path: '**',
        redirectTo: 'asignatura'
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
export class AsignaturaRoutingModule { }
