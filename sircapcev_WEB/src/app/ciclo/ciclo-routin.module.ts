import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CicloComponent } from './page/ciclo/ciclo.component';


const routes: Routes = [
  {
    path: '',
    children: [
      
      {
        path: 'ciclo',
        component: CicloComponent
      },
      {
        path: '**',
        redirectTo: 'ciclo'
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
export class CicloRoutinModule { }
