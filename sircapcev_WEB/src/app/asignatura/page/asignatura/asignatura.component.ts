import { Component, OnInit, ViewChild } from '@angular/core';
import { Asignatura } from 'src/app/interface/asignatura.interface';
import { AsignaturaService } from 'src/app/service/asignatura.service';

@Component({
  selector: 'app-asignatura',
  templateUrl: './asignatura.component.html',
  styleUrls: ['./asignatura.component.css']
})
export class AsignaturaComponent implements OnInit {

  asignatura: Asignatura[]=[];
  displayedColumns: string[] = ["asignaturas_ID","descripcion"];

  constructor(private asignaturaService: AsignaturaService) { }
  ngOnInit(): void {
    this.getAsignatura();
  }

getAsignatura (): void {
  this.asignaturaService.list().subscribe({
    next: (asignatura: Asignatura[]) => {
      this.asignatura = asignatura;
    },
    error: (error) => {
      console.error('Error obteniendo las asignaturas:', error);
    }
  });
}

visualizarAsignatura(id: number): void {
  console.log('Visualizar asignatura con id:', id);
}
}