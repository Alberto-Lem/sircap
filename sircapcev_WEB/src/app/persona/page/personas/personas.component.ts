import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-personas',
  templateUrl: './personas.component.html',
  styleUrls: ['./personas.component.css']
})
export class PersonasComponent {
  @Input() title: string = '';
  personanavLinks = [
    { path: 'listadopersona', label: 'Listado Persona' },
    { path: 'ingresarpersona', label: 'Ingresar Persona' },
  ];
}
