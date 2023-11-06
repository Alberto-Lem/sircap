import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { PlanesService } from 'src/app/service/planes.service';
import { of } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Planes } from '../../planesinterface/planes.interface';

@Component({
  selector: 'app-agregarplanes',
  templateUrl: './agregarplanes.component.html',
  styleUrls: ['./agregarplanes.component.css']
})
export class AgregarplanesComponent implements OnInit {
  planesForm!: FormGroup;
  editMode: boolean = false;
  planes: Planes | null = null;

  constructor(
    private fb: FormBuilder,
    private planesService: PlanesService,
    private router: Router,
    private toast: ToastrService,
    private activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    // Inicializa el formulario de planes
    this.initializeForm();

    this.activatedRoute.params.pipe(
      switchMap(({ id }) => {
        if (id) {
          this.editMode = true;
          return this.planesService.detail(id);
        } else {
          return of(null);
        }
      })
    ).subscribe(planesForm => {
      if (planesForm) {
        this.planes = planesForm;
        this.planesForm.patchValue(planesForm);
      }
    });
  }

  initializeForm() {
    this.planesForm = this.fb.group({
      noPlan: ['', Validators.required],
      nombrePlan: ['', Validators.required],
      cantidadParticipante: ['', Validators.required],
      estado: ['', Validators.required],
      fechaInicio: ['', Validators.required],
      fechaFinalizacion: ['', Validators.required],
      modalidad: ['', Validators.required],
      poblacion: ['', Validators.required],
      encargado: ['', Validators.required],
      lugarEjecucion: ['', Validators.required],
      comentario: [''],
    });
  }


  guardar() {
    if (this.editMode) {
      if (this.planes && this.planesForm.valid) {
        const planesData = this.planesForm.value;
        this.planesService.update(this.planes.id!, planesData).subscribe({
          next: () => {
            this.router.navigate(['/planess']);
            this.toast.success('La planes se ha actualizado correctamente', 'Éxito', { timeOut: 3000, positionClass: 'toast-top-center' });
          },
          error:(error: any) => {
            this.toast.error('Error al actualizar la planes: ' + error.error.message, 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
            console.error("Error al actualizar la planes:", error);
          }
      });
      }
    } else {
      if (this.planesForm.valid) {
        const planesData = this.planesForm.value;
        this.planesService.create(planesData).subscribe({
          next: () => {
            this.router.navigate(['/planes']);
            this.toast.success('Los datos de la planes se han guardado correctamente', 'Éxito', { timeOut: 3000, positionClass: 'toast-top-center' });
          },
          error:(error: any) => {
            this.toast.error('Error al guardar los datos de la planes: ' + error.error.message, 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
            console.error("Error al guardar datos de la planes:", error);
          }
      });
      }
    }
  }
}