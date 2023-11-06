import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Inscripciones } from '../interface/inscripciones.interface';

@Injectable({
  providedIn: 'root'
})
export class InscripcionesService {
  inscripcionesURL = environment.apiUrl + '/inscripciones';

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<Inscripciones[]> {
    return this.httpClient.get<Inscripciones[]>(this.inscripcionesURL);
  }

  public detail(id: string): Observable<Inscripciones> {
    return this.httpClient.get<Inscripciones>(this.inscripcionesURL + `/${id}`);
  }

  public uploadCsvFile(file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file, file.name);
  
    return this.httpClient.post<any>(this.inscripcionesURL, formData);
  }
  
  
  public create(inscripciones: Inscripciones): Observable<any> {
    return this.httpClient.post<any>(this.inscripcionesURL, inscripciones);
  }

  public update(id: string, inscripciones: Inscripciones): Observable<any> {
    return this.httpClient.put<any>(this.inscripcionesURL + `/${id}`, inscripciones);
  }

  public delete(id: string): Observable<any> {
    return this.httpClient.delete<any>(this.inscripcionesURL + `/${id}`);
  }
}

