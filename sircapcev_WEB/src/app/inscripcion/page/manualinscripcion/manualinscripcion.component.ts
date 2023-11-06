import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Inscripciones } from 'src/app/interface/inscripciones.interface';
import { InscripcionesService } from 'src/app/service/inscripciones.service';

@Component({
  selector: 'app-manualinscripcion',
  templateUrl: './manualinscripcion.component.html',
  styleUrls: ['./manualinscripcion.component.css']
})
export class ManualinscripcionComponent {
  inscripcion: Inscripciones = {
    nip: '',
    noPlan: '',
    tipo: 'Manual',
    estado: '',
    observaciones: ''
  };

 
  constructor(
    private inscripcionesService: InscripcionesService,
    private router: Router,
    private toast: ToastrService,
    private activatedRoute: ActivatedRoute,
  ) {}

  // Método para enviar el formulario y crear una nueva inscripción
  submitForm(): void {
    this.inscripcionesService.create(this.inscripcion)
    .subscribe({
      next:  (inscripcion: Inscripciones) => {
        // Manejar la respuesta exitosa (puedes mostrar un mensaje de éxito, redirigir, etc.)
        this.toast.success("Inscripción realizado con éxito", 'Éxito', { timeOut: 3000, positionClass: 'toast-top-center' });
      },

      error: (error: any) => {
        this.toast.error(error.error.message, 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
      }
    });
  }
}
