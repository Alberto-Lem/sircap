import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MaterialModule } from '../material/material.module';
import { PlanesRoutingModule } from './planes-routing.module';
import { ListadoplanesComponent } from './pages/listado/listadoplanes.component';
import { PlanesComponent } from './pages/dashplanes/planes.component';
import { EstadoplanesComponent } from './pages/estadoplanes/estadoplanes.component';
import { DetailPlanesComponent } from './pages/detail-planes/detail-planes.component';
import { AgregarplanesComponent } from './pages/agregar/agregarplanes.component';

@NgModule({
  declarations: [
    PlanesComponent,
    PlanesComponent,
    ListadoplanesComponent,
    EstadoplanesComponent,
    DetailPlanesComponent,
    AgregarplanesComponent
  ],
  imports: [
    CommonModule,
    PlanesRoutingModule,
    MaterialModule
  ]
})
export class PlanesModule { }
