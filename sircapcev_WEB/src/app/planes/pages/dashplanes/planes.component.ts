import { Component } from '@angular/core';

@Component({
  selector: 'app-planes',
  templateUrl: './planes.component.html',
  styleUrls: ['./planes.component.css']
})
export class PlanesComponent {
  planesnavLinks = [
    { path: 'listadoplanes', label: 'Listado' },
    { path: 'estado', label: 'Estado de los planes' },
    { path: 'agregarplanes', label: 'Agregar planes' },
  ];
}
