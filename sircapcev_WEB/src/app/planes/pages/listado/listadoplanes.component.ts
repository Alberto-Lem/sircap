import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from 'src/app/service/token.service';
import { PlanesService } from '../../../service/planes.service';
import { Planes } from '../../planesinterface/planes.interface';

@Component({
  selector: 'app-listadoplanes',
  templateUrl: './listadoplanes.component.html',
  styleUrls: ['./listadoplanes.component.css'],
})
export class ListadoplanesComponent implements OnInit {
  planes: Planes[] = [];
  planesToShow: Planes[] = [];
  isLogged: boolean = false;
  isAdmin: boolean = false;
  pageSize: number = 6;
  currentPage: number = 0;
  paginatorLength: number = 0;
  loading: boolean = false;
  loadingText: string = 'Cargando';
  loadingDots: string = '';
  constructor(
    private planesService: PlanesService,
    private router: Router,
    private toast: ToastrService,
    private tokenService: TokenService
  ) {}

  ngOnInit(): void {
    this.loading = true;
    this.isLogged = this.tokenService.isLogged();
    this.isAdmin = this.tokenService.isAdmin();
    this.getPlanes();
  }

  getPlanes(): void {
    this.loading = true;
    this.loadingText = 'Cargando';
    let dotCount = 0;

    const dotInterval = setInterval(() => {
      this.loadingDots = '.'.repeat(dotCount);
      dotCount = (dotCount + 1) % 4; // Reinicia los puntos después de 3
    }, 500);
  
    this.planesService.list().subscribe({
      next: (data: Planes[]) => {
        this.planes = data;
        this.paginatorLength = this.planes.length;
        this.updatePlanes();
        this.loading = false;
        clearInterval(dotInterval); // Detiene el temporizador
        this.loadingDots = ''; // Restablece los puntos suspensivos
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error', {
          timeOut: 3000,
          positionClass: 'toast-top-center',
        });
        this.loading = false;
        clearInterval(dotInterval); // Detiene el temporizador
        this.loadingDots = ''; // Restablece los puntos suspensivos
      },
    });
  }
  
  
  applyFilter(event: Event): void {
    const filtro = (event.target as HTMLInputElement).value.trim().toLowerCase();

    if (filtro === '') {
      this.getPlanes();
    } else {
      this.planes = this.planes.filter((planes) => {
        const noPlan = planes.nombrePlan.toLowerCase();
        const nombrePlan = planes.nombrePlan.toLowerCase();
        const encargado = planes.encargado.toLowerCase();
        const estado = planes.estado.toLowerCase();
        const modalidad = planes.modalidad.toLowerCase();
        const lugarEjecucion = planes.lugarEjecucion.toLowerCase();
        const poblacion = planes.poblacion.toLowerCase();
        
        return noPlan.includes(filtro)
        || nombrePlan.includes(filtro)
        || encargado.includes(filtro)
        || estado.includes(filtro)
        || modalidad.includes(filtro)
        || lugarEjecucion.includes(filtro)
        || poblacion.includes(filtro);
        
      });
      this.paginatorLength = this.planes.length;
      this.updatePlanes();
    }
  }

  changePage(event: PageEvent): void {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.updatePlanes();
  }

  updatePlanes(): void {
    const startIndex = this.currentPage * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.planesToShow = this.planes.slice(startIndex, endIndex);
  }
  

  redirectToDetailCourse(courseId: string): void {
    this.router.navigate(['/planes/listadoplanes', courseId]);
  }

  redirectToEditCourse(courseId: string): void {
    this.router.navigate(['/planes/editar', courseId]);
  }

  
}
