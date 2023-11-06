import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { ProcesamientoDatosService } from 'src/app/service/procesamientoDatos.service';
import { Serie } from '../../interfaces/conteoPlanesGeneros.interface';

@Component({
  selector: 'app-grafica-dona1',
  templateUrl: './grafica-dona1.component.html',
  styleUrls: ['./grafica-dona1.component.css']
})
export class GraficaDona1Component {
  viewGenero: [number, number] = [700, 200];

  // options
  gradient: boolean = true;
  showLegend: boolean = true;
  showLabels: boolean = true;
  isDoughnut: boolean = false;

  public generoData: Serie[] = [];

  constructor(
    private procesamientoDatosService: ProcesamientoDatosService,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
    this.obtenerConteoPorGenero();
  }

  
  obtenerConteoPorGenero() {
    this.procesamientoDatosService.getConteoPorGenero().subscribe({
      next: (data: Serie[]) => {
        this.generoData = data;
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error', {
          timeOut: 3000,
          positionClass: 'toast-top-center',
        });
      }
    });
  }


  get genero() {
    return this.generoData
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
