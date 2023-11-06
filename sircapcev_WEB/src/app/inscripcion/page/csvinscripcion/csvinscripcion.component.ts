import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { InscripcionesService } from 'src/app/service/inscripciones.service';

@Component({
  selector: 'app-csvinscripcion',
  templateUrl: './csvinscripcion.component.html',
  styleUrls: ['./csvinscripcion.component.css']
})
export class CsvinscripcionComponent {
  fileUploadResponse: string | null = null;
  fileToUpload: File | null = null;

  constructor(private inscripcionesService: InscripcionesService) {}

  uploadFile() {
    if (!this.fileToUpload) {
      this.fileUploadResponse = 'Por favor, selecciona un archivo antes de cargarlo.';
      return;
    }

    this.inscripcionesService.uploadCsvFile(this.fileToUpload).subscribe(
      (response: any) => {
        this.fileUploadResponse = response.message; // Ajusta el formato de la respuesta según tu API
      },
      (error) => {
        console.error('Error al cargar el archivo', error);
        this.fileUploadResponse = 'Error al cargar el archivo.';
      }
    );
  }

  onFileSelected(event: any) {
    this.fileUploadResponse = null; // Limpia mensajes anteriores
    const file: File = event.target.files[0];
    if (file) {
      this.fileToUpload = file;
    } else {
      this.fileToUpload = null;
    }
  }

  uploadSelectedFile() {
    this.uploadFile();
  }
}
