import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Persona } from 'src/app/interface/persona.interface';
import { MessageService } from 'src/app/service/message.service';
import { PersonaService } from 'src/app/service/persona.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-listadopersona',
  templateUrl: './listadopersona.component.html',
  styleUrls: ['./listadopersona.component.css']
})
export class ListadopersonaComponent implements OnInit {
  isAdmin: boolean = false;
  filteredPersona: Persona[] = [];
  searchTerm: string = "";
  showProgressBar: boolean = false;
  persona: Persona[] = [];
  displayedColumns: string[] = [
    "no",
    "gradoPolicial",
    "nombres",
    "apellidos",
    "nip",
    "genero",
    "accion"
  ];
  
  dataSource: MatTableDataSource<Persona> = new MatTableDataSource<Persona>([]);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private router: Router,
    private personaService: PersonaService,
    private toast: ToastrService,
    private tokenService: TokenService,
    private messageService: MessageService<Persona>
  ) { }

  ngOnInit(): void {
    this.showProgressBar= true;
    this.getPersonas();
    this.isAdmin = this.tokenService.isAdmin();
    if (!this.isAdmin) {
      const index = this.displayedColumns.indexOf('accion');
      if (index !== -1) {
        this.displayedColumns.splice(index, 1);
      }
    }
  }

  getPersonas(): void {
    this.personaService.list().subscribe({
      next: (data: Persona[]) => {
        this.persona = data;
        this.filteredPersona = this.persona;

        // Agregar números consecutivos a cada fila
        this.filteredPersona.forEach((persona, index) => {
          persona.no = index + 1;
        });

        this.dataSource = new MatTableDataSource<Persona>(this.filteredPersona);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.showProgressBar = false;
      },
      error: (err) => {
        this.toast.error(err.error.message, 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
        this.showProgressBar = false;
      }
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  onDelete(id: string): void {
    this.toast.warning('Are you sure? You cannot undo', 'Confirmation', {
      closeButton: true,
      disableTimeOut: true,
      extendedTimeOut: 0,
    }).onTap
    .subscribe(() => {
      // El usuario hizo clic en el toast (Aceptar)
      this.performDelete(id);
    });
  
    // Temporizador para manejar el caso en que el toast se cierre sin clic del usuario (Cancelar)
    setTimeout(() => {
      // El toast se cerró sin clic del usuario (Cancelar)
      this.toast.error('Persona not deleted', 'Canceled', { timeOut: 3000, positionClass: 'toast-top-center' });
    }, 0);
  }
  
  private performDelete(id: string): void {
    this.personaService.delete(id).subscribe({
      next: (data) => {
        this.toast.success(data.message, 'Success', { timeOut: 3000, positionClass: 'toast-top-center' });
        this.getPersonas();
      },
      error: (err) => {
        this.toast.error(err.error.message, 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
      }
    });
  }
  
  redirectToDetailPersona(personaId: string): void {
    this.router.navigate(['/personas/listadopersona', personaId]);
  }

  redirectToEditPersona(personaId: string): void {
    this.router.navigate(['/personas/editar', personaId]);
  }
}
