import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { PersonaService } from 'src/app/service/persona.service';
import { Persona } from 'src/app/interface/persona.interface';
import { of } from 'rxjs';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-ingresarpersona',
  templateUrl: './ingresarpersona.component.html',
  styleUrls: ['./ingresarpersona.component.css']
})
export class IngresarpersonaComponent implements OnInit {
  personaForm!: FormGroup;
  editMode: boolean = false;
  persona: Persona | null = null;
  idiomas: string[] = ['Español', 'Inglés', 'Francés', 'Alemán', 'Italiano', 'Otros'];

  constructor(
    private fb: FormBuilder,
    private personaService: PersonaService,
    private router: Router,
    private toast: ToastrService,
    private activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    // Inicializa el formulario de persona
    this.initializeForm();

    this.activatedRoute.params.pipe(
      switchMap(({ id }) => {
        if (id) {
          this.editMode = true;
          return this.personaService.detail(id);
        } else {
          return of(null);
        }
      })
    ).subscribe(personaForm => {
      if (personaForm) {
        this.persona = personaForm;
        this.personaForm.patchValue(personaForm);
      }
    });
  }

  initializeForm() {
    this.personaForm = this.fb.group({
      nip: ['', Validators.required],
      gradoPolicial: ['', Validators.required],
      nombres: ['', Validators.required],
      apellidos: ['', Validators.required],
      genero: ['', Validators.required],
      dpi: ['', Validators.required],
      nit: ['', Validators.required],
      noAfiliacionIgss: [''],
      renglonPresupuestario: ['', Validators.required],
      fechaDeNacimiento: ['', Validators.required],
      direccionResidencial: ['', Validators.required],
      lugarDeServicio: ['', Validators.required],
      destinoActual:[''],
      dependenciaDondeLabora: ['', Validators.required],
      telefono: ['', Validators.required],
      correoElectronicoPersonal: ['', [Validators.required, Validators.email]],
      correoElectronicoInstitucional: ['', [Validators.email]],
      grupoEtnico: ['', Validators.required],
      escolaridad: ['', Validators.required],
      tituloObtenido: ['', Validators.required],
      numeroColegiadoActivo: [''],
      idiomasQueDomina: this.fb.array([]),
      estudiaActualmente: ['No'],
      nombreCarrera: [''],
      otrosEstudiosRealizados:['']
    });
  

    // Agrega un listener al cambio de "estudiaActualmente" para habilitar/deshabilitar "nombreCarrera"
    const estudiaActualmenteControl = this.personaForm.get('estudiaActualmente');
    if (estudiaActualmenteControl && estudiaActualmenteControl.value === 'Si') {
      const nombreCarreraControl = this.personaForm.get('nombreCarrera');
      if (nombreCarreraControl) {
        nombreCarreraControl.enable(); // Habilitar el campo
      }
    }
  }
  
    // Función para obtener el FormArray idiomasQueDomina
    get idiomasQueDomina() {
      return this.personaForm.get('idiomasQueDomina') as FormArray;
    }
  
    // Función para agregar un idioma seleccionado al FormArray
    agregarIdioma(idioma: string) {
      this.idiomasQueDomina.push(this.fb.control(idioma));
    }
  
    // Función para eliminar un idioma del FormArray
    eliminarIdioma(index: number) {
      this.idiomasQueDomina.removeAt(index);
    }

    toggleIdioma(idioma: string) {
      const idiomasSeleccionados = this.idiomasQueDomina.value;
      const index = idiomasSeleccionados.indexOf(idioma);
    
      if (index !== -1) {
        this.idiomasQueDomina.removeAt(index); // Si ya está seleccionado, lo quitamos
      } else {
        this.idiomasQueDomina.push(this.fb.control(idioma)); // Si no está seleccionado, lo agregamos
      }
    }
    

  guardar() {
    if (this.editMode) {
      if (this.persona && this.personaForm.valid) {
        const personaData = this.personaForm.value;
        this.personaService.update(this.persona.id!, personaData).subscribe({
          next: (response: any) => {
            this.router.navigate(['/personas']);
            this.toast.success('La persona se ha actualizado correctamente', 'Éxito', { timeOut: 3000, positionClass: 'toast-top-center' });
          },
          error:(error: any) => {
            this.toast.error('Error al actualizar la persona: ' + error.error.message, 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
            console.error("Error al actualizar la persona:", error);
          }
      });
      }
    } else {
      if (this.personaForm.valid) {
        const personaData = this.personaForm.value;
        this.personaService.create(personaData).subscribe({
          next: (persona: Persona) => {
            this.router.navigate(['/personas']);
            this.toast.success('Los datos de la persona se han guardado correctamente', 'Éxito', { timeOut: 3000, positionClass: 'toast-top-center' });
          },
          error:(error: any) => {
            this.toast.error('Error al guardar los datos de la persona: ' + error.error.message, 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
            console.error("Error al guardar datos de la persona:", error);
          }
      });
      }
    }
  }
}