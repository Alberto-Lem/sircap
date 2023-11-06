import { Component } from '@angular/core';
import { ProcesamientoDatosService } from 'src/app/service/procesamientoDatos.service';
import { Serie } from '../../interfaces/conteoPlanesGeneros.interface';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-grafico-dona',
  templateUrl: './grafico-dona.component.html',
  styleUrls: ['./grafico-dona.component.css']

})
export class GraficoDonaComponent {
  viewGrado: [number, number] = [700, 200];

  // options
  gradient: boolean = true;
  showLegend: boolean = true;
  showLabels: boolean = true;
  isDoughnut: boolean = false;

  public anioData: Serie[] = [];
  public years: number[] = [2019, 2020, 2021, 2022, 2023, 2024];
  public selectedStartYear: number = 2019;
  public selectedEndYear: number = 2023;

  constructor(
    private procesamientoDatosService: ProcesamientoDatosService,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
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
      this.procesamientoDatosService.getConteoPorAnio(filtro).subscribe({
        next: (data: Serie[]) => {
          this.anioData = data;
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


  get single() {
    return this.anioData;
  }

  onSelect(data: any): void {
    JSON.parse(JSON.stringify(data));
  }

  onActivate(data: any): void {
    JSON.parse(JSON.stringify(data));
  }

  onDeactivate(data: any): void {
    JSON.parse(JSON.stringify(data));
  }
}
