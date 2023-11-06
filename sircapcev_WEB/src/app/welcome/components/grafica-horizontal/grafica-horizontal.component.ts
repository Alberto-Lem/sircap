import { Component } from '@angular/core';
import { PlanesService } from 'src/app/service/planes.service';

@Component({
  selector: 'app-grafica-horizontal',
  templateUrl: './grafica-horizontal.component.html',
  styleUrls: ['./grafica-horizontal.component.css']
})
export class GraficaHorizontalComponent {
  view: [number, number] = [1700, 300];

  // options
  showXAxis: boolean = true;
  showYAxis: boolean = true;
  gradient: boolean = false;
  showLegend: boolean = true;
  showXAxisLabel: boolean = true;
  yAxisLabel: string = 'Subdirecciones';
  showYAxisLabel: boolean = true;
  xAxisLabel: string = 'Cantidad';

  
  constructor(private planesService: PlanesService) { }
  get single() {
    return this.planesService.abcData;
  }
 

  onSelect(data: any): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data: any): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data: any): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }
}
