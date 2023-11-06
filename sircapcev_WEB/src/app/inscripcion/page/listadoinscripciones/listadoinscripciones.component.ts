import { Component } from '@angular/core';
import { Inscripciones } from 'src/app/interface/inscripciones.interface';
import { InscripcionesService } from 'src/app/service/inscripciones.service';

@Component({
  selector: 'app-listadoinscripciones',
  templateUrl: './listadoinscripciones.component.html',
  styleUrls: ['./listadoinscripciones.component.css']
})
export class ListadoinscripcionesComponent {

  inscripciones: Inscripciones[] = [];

  constructor(private inscripcionesService: InscripcionesService) {}

  ngOnInit(): void {
    this.loadInscripciones();
  }

  loadInscripciones() {
    this.inscripcionesService.list().subscribe(
      (data) => {
        this.inscripciones = data;
      },
      (error) => {
        console.error('Error al cargar las inscripciones', error);
      }
    );
  }
}
