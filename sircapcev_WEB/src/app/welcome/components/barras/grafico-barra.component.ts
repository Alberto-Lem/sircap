import { Component } from '@angular/core';
import { PlanConConteo } from '../../interfaces/conteoPlanesGeneros.interface';
import { ProcesamientoDatosService } from 'src/app/service/procesamientoDatos.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-grafico-barra',
  templateUrl: './grafico-barra.component.html',
  styleUrls: ['./grafico-barra.component.css']
})
export class GraficoBarraComponent {

  view: [number, number] = [700, 410];

  // options
  showXAxis: boolean = true;
  showYAxis: boolean = true;
  gradient: boolean = true;
  showLegend: boolean = true;
  showXAxisLabel: boolean = true;
  xAxisLabel: string = 'Planes';
  showYAxisLabel: boolean = true;
  yAxisLabel: string = 'Capacitados';
  legendTitle: string = 'Genero';

  public planesData: PlanConConteo[] = [];
  public years: number[] = [2019, 2020, 2021, 2022, 2023, 2024];
  public selectedStartYear: number = 2023;
  public selectedEndYear: number = 2023;

  constructor(
    private procesamientoDatosService: ProcesamientoDatosService,
    private toast: ToastrService
    ) { }

  ngOnInit(): void {
    this.filtrarDatos();
  }

  restablecerDatos() {
    this.selectedEndYear = 2023;
    this.selectedStartYear = 2023;
    this.filtrarDatos();
  }

  filtrarDatos() {
    if (this.selectedStartYear !== undefined && this.selectedEndYear !== undefined) {
      if (this.selectedStartYear > this.selectedEndYear) {
        this.toast.warning('Año final debe ser mayor', 'Confirmation', {
          closeButton: true,
          disableTimeOut: true,
          extendedTimeOut: 0,
        }).onTap
        .subscribe(() => {
          this.selectedStartYear = this.selectedEndYear;
          this.filtrarDatos();
        });
        return;
      }

      const filtro = { anioInicio: this.selectedStartYear, anioFin: this.selectedEndYear };
      this.procesamientoDatosService.getConteoDatosFiltrado(filtro).subscribe({
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
