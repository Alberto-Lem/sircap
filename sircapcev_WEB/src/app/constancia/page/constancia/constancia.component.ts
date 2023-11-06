import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { ConstanciaCurso } from 'src/app/interface/constanciaCurso.interface';
import { ContanciaCursoService } from 'src/app/service/contanciaCurso.service';

@Component({
  selector: 'app-constancia',
  templateUrl: './constancia.component.html',
  styleUrls: ['./constancia.component.css']
})
export class ConstanciaComponent implements OnInit {
  constancia1: any = {
    nombre: 'Juan Pérez',
    nombreCurso: 'Curso de Desarrollo Web',
    duracion: '40',
    fechaFinalizacion: '15 de septiembre de 2023',
    nombreInstructor: 'María González',
    firmaElectronica: '8298129 23123912 1'
  };

  constancia!: ConstanciaCurso[];
  displayedColumns: string[] = ["id","nota","descripcion"];
  dataSource = new MatTableDataSource<ConstanciaCurso>(this.constancia);

  constructor(private constanciaService: ContanciaCursoService) { }
  @ViewChild(MatTable) table!: MatTable<ConstanciaCurso>;

  ngOnInit(): void {
    this.getConstancias();
  }
  public getConstancias(){
    this.constanciaService.list().subscribe({
      next: (constancia: ConstanciaCurso[]) => {
        this.constancia = constancia;
        this.dataSource.data = this.constancia;
      },
      error: (error) => {
        console.error('Error obteniendo las constancias:', error);
      }
    });
  }

}
