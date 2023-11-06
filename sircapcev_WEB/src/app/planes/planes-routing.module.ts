import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlanesComponent } from './pages/dashplanes/planes.component';
import { ListadoplanesComponent } from './pages/listado/listadoplanes.component';
import { EstadoplanesComponent } from './pages/estadoplanes/estadoplanes.component';
import { DetailPlanesComponent } from './pages/detail-planes/detail-planes.component';
import { AgregarplanesComponent } from './pages/agregar/agregarplanes.component'; 

const rutas: Routes = [
  {
    path: '',
    component: PlanesComponent,
    children: [
      {
        path: 'listadoplanes',
        component: ListadoplanesComponent,
      },
      {
        path: 'listadoplanes/:id',
        component: DetailPlanesComponent,
      },
      {
        path: 'estado',
        component: EstadoplanesComponent,
      },
      {
        path: 'agregarplanes',
        component: AgregarplanesComponent,
        pathMatch: 'full'
      },
      {
        path: 'editar/:id',
        component: AgregarplanesComponent,
      },
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'listadoplanes',
      },
    ]
  }
]



@NgModule({
  imports: [
    RouterModule.forChild(rutas)
  ],
  exports: [
    RouterModule
  ]
})
export class PlanesRoutingModule { }
