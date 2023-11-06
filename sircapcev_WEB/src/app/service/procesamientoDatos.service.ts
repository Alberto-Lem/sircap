import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { PlanConConteo, Serie } from '../welcome/interfaces/conteoPlanesGeneros.interface';

@Injectable({
  providedIn: 'root'
})
export class ProcesamientoDatosService {
  procesamientoDatosURL = environment.apiUrl + '/procesamientoDatos';

  constructor(private httpClient: HttpClient) { }

  public getConteoDatosFiltrado(filtro: { anioInicio: number, anioFin: number }): Observable<PlanConConteo[]> {
    return this.httpClient.post<PlanConConteo[]>(this.procesamientoDatosURL + '/planes_generos_anio', filtro);
  }

  public getConteoPorAnio(filtro: { anioInicio: number, anioFin: number }): Observable<Serie[]> {
    return this.httpClient.post<Serie[]>(this.procesamientoDatosURL + '/conteo_por_anio', filtro);
  }

  public getConteoPorGenero(): Observable<Serie[]> {
    return this.httpClient.get<Serie[]>(this.procesamientoDatosURL + '/conteo_por_genero');
  }

  public getConteoGradoPorAnio(filtro: { fechaInicio: Date, fechaFin: Date }): Observable<PlanConConteo[]> {
    return this.httpClient.post<PlanConConteo[]>(this.procesamientoDatosURL + '/conteo_grado_anio', filtro);
  }

}
