import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ConstanciaComponent } from './page/constancia/constancia.component';


const routes: Routes = [
  {
    path: '',
    children: [
      
      {
        path: 'constancia',
        component: ConstanciaComponent
      },
      {
        path: '**',
        redirectTo: 'constancia'
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
export class ConstanciaRoutinModule { }
