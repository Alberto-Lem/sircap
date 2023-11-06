import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Inscripciones } from 'src/app/interface/inscripciones.interface';
import { InscripcionesService } from 'src/app/service/inscripciones.service';
import { MessageService } from 'src/app/service/message.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-inscripciones',
  templateUrl: './inscripciones.component.html',
  styleUrls: ['./inscripciones.component.css']
})
export class InscripcionesComponent implements OnInit {
  inscripciones: Inscripciones[] = [];
  insert: Inscripciones = {
    nip: '',
    noPlan: '',
    estado: '',
    observaciones: ''
  };
  isLogged: boolean = false;
  isAdmin: boolean = false;

  constructor(
    private inscripcionesService: InscripcionesService,
    private toast: ToastrService,
    private messageService: MessageService<Inscripciones>,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
    this.isAdmin = this.tokenService.isAdmin();
    this.getInscripciones();
  }

  guardar(): void {
    this.inscripcionesService.create(this.insert).subscribe({
      next: () => {
        this.toast.success('Inscripción creada con éxito', 'Éxito', {
          timeOut: 3000,
          positionClass: 'toast-top-center'
        });
      },
      error: (error: any) => {
        this.toast.error(error.error.message, 'Error', {
          timeOut: 3000,
          positionClass: 'toast-top-center'
        });
        console.error('Error al crear la inscripción:', error);
      }
    });
  }

  getInscripciones(): void {
    this.inscripcionesService.list().subscribe({
      next: (data: Inscripciones[]) => {
        this.inscripciones = data;
      },
      error: (err) => {
        this.toast.error(err.error.message, 'Error', {
          timeOut: 3000,
          positionClass: 'toast-top-center'
        });
      }
    });
  }

  onDelete(id: string): void {
    this.toast.warning('¿Estás seguro? No puedes deshacerlo', 'Confirmación', {
      closeButton: true,
      disableTimeOut: true,
      extendedTimeOut: 0
    }).onTap.subscribe(() => {
      this.performDelete(id);
    });

    setTimeout(() => {
      this.toast.error('Inscripción no eliminada', 'Cancelado', {
        timeOut: 3000,
        positionClass: 'toast-top-center'
      });
    }, 0);
  }

  private performDelete(id: string): void {
    this.inscripcionesService.delete(id).subscribe({
      next: (data) => {
        this.toast.success(data.message, 'Éxito', {
          timeOut: 3000,
          positionClass: 'toast-top-center'
        });
        this.getInscripciones();
      },
      error: (err) => {
        this.toast.error(err.error.message, 'Error', {
          timeOut: 3000,
          positionClass: 'toast-top-center'
        });
      }
    });
  }

  sendInscripciones(inscripcion: Inscripciones): void {
    this.messageService.sendMessage(inscripcion);
  }
}
