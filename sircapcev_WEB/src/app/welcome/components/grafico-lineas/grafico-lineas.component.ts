import { Component } from '@angular/core';
import { PlanConConteo } from '../../interfaces/conteoPlanesGeneros.interface';
import { ProcesamientoDatosService } from 'src/app/service/procesamientoDatos.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-grafico-lineas',
  templateUrl: './grafico-lineas.component.html',
  styleUrls: ['./grafico-lineas.component.css']
})
export class GraficoLineasComponent {
  view: [number, number] = [1700, 600];

  // options
  Grado: boolean = true;
  showLabels: boolean = true;
  animations: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  showXAxisLabel: boolean = true;
  xAxisLabel: string = 'Año';
  yAxisLabel: string = 'Grado Policial';
  timeline: boolean = true;

  public planesData: PlanConConteo[] = [];
  public inicio: Date = new Date("2019-09-23");
  public fin: Date = new Date("2023-10-31");
  public inicioStr: string | undefined;
  public finStr: string | undefined;

  constructor(
    private procesamientoDatosService: ProcesamientoDatosService,
    private toast: ToastrService
  ) { }



  ngOnInit(): void {
    this.inicioStr = this.formatDate(this.inicio);
    this.finStr = this.formatDate(this.fin);
    this.filtrarDatos();
  }

  formatDate(date: Date): string {
    return date.toISOString().split('T')[0];
  }

  filtrarDatos() {
    if (this.inicioStr) {
      this.inicio = new Date(this.inicioStr);
    }
    if (this.finStr) {
      this.fin = new Date(this.finStr);
    }
    if (this.inicio !== undefined && this.fin !== undefined) {
      if (this.inicio > this.fin) {
        this.toast.warning('fecha fin debe ser mayor', 'Confirmation', {
          closeButton: true,
          disableTimeOut: true,
          extendedTimeOut: 0,
        }).onTap
          .subscribe(() => {
            this.inicio = this.fin;
            this.filtrarDatos();
          });
        return;
      }

      const filtro = { fechaInicio: this.inicio, fechaFin: this.fin };
      this.procesamientoDatosService.getConteoGradoPorAnio(filtro).subscribe({
        next: (data: PlanConConteo[]) => {
          this.planesData = data;
        },
        error: (error) => {
          this.toast.error(error.error.message, 'Error', {
            timeOut: 3000,
            positionClass: 'toast-top-center',
          });
        }
      });
    }
  }
  restablecerFechas() {
    this.inicio = new Date("2019-09-23");
    this.fin = new Date("2023-10-31");
    this.inicioStr = this.formatDate(this.inicio);
    this.finStr = this.formatDate(this.fin);
    this.filtrarDatos();
  }




  get multi() {
    return this.planesData;
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
