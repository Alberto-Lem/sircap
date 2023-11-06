import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Ciclo } from 'src/app/interface/ciclo.interface';
import { CicloService } from 'src/app/service/ciclo.service';

@Component({
  selector: 'app-ciclo',
  templateUrl: './ciclo.component.html',
  styleUrls: ['./ciclo.component.css']
})
export class CicloComponent implements OnInit {

  ciclo!: Ciclo[];
  displayedColumns: string[] = ["ciclo_ID", "asignaturas","CTS","Requisitos"];
  dataSource = new MatTableDataSource<Ciclo>(this.ciclo);

  constructor(private cicloService: CicloService) { }
  @ViewChild(MatTable) table!: MatTable<Ciclo>;

  ngOnInit(): void {
    this.getCiclos();
  }
  public getCiclos() {
    this.cicloService.list().subscribe({
      next: (ciclo: Ciclo[]) => {
        this.ciclo = ciclo;
        this.dataSource.data = this.ciclo;
      },
      error: (error) => {
        console.error('Error obteniendo los ciclos:', error);
      }
    });
  }

}