import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MasivaCargaService {
  constructor(private http: HttpClient) {}

  uploadData(file: File, endpoint: string) {
    const formData = new FormData();
    formData.append('csvFile', file, file.name);

    return this.http.post<any>(endpoint, formData);
  }
}
