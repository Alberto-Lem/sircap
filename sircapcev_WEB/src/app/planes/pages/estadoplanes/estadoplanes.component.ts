import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component } from '@angular/core';
import { PlanesService } from 'src/app/service/planes.service';
import { Planes } from '../../planesinterface/planes.interface';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';


export class DialogAnimationsExampleDialog {
  constructor(public dialogRef: MatDialogRef<DialogAnimationsExampleDialog>) {}
}


@Component({
  selector: 'app-estadoplanes',
  templateUrl: './estadoplanes.component.html',
  styleUrls: ['./estadoplanes.component.css'],
})

export class EstadoplanesComponent {
  a: Planes[] = [];
  b: Planes[] = [];
  c: Planes[] = [];
  d: Planes[] = [];
  e: Planes[] = [];

  constructor(private planesService: PlanesService,
    ) {}

  ngOnInit(): void {
    this.planesService.list().subscribe((planes: Planes[]) => {
      this.a = planes.filter(plan => plan.estado === 'Por ejecutar');
      this.b = planes.filter(plan => plan.estado === 'Ejecutando');
      this.c = planes.filter(plan => plan.estado === 'Suspendido');
      this.d = planes.filter(plan => plan.estado === 'Ejecutado');
      this.e = planes.filter(plan => plan.estado === 'Archivar y ocultar');
    });
  }


  drop(event: CdkDragDrop<Planes[]>): void {
    const { previousContainer, container, previousIndex, currentIndex } = event;

    if (previousContainer === container) {
      this.moverElementoDentroDeArray(container.data, previousIndex, currentIndex);
    } else {
      this.transferirElementoEntreArrays(previousContainer.data, container.data, previousIndex, currentIndex);
    }
  }

  private moverElementoDentroDeArray(array: Planes[], prevIndex: number, currIndex: number): void {
    moveItemInArray(array, prevIndex, currIndex);
  }

  private transferirElementoEntreArrays(sourceArray: Planes[], targetArray: Planes[], prevIndex: number, currIndex: number): void {
    transferArrayItem(sourceArray, targetArray, prevIndex, currIndex);
  }
}
