import { Component, HostListener, OnInit } from '@angular/core';
import { Subscription, interval } from 'rxjs';
import { TokenService } from './service/token.service';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from './auth/page/login-dialog/login-dialog.component';
import { PerfilDialogComponent } from './auth/page/perfil-dialog/perfil-dialog.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'sircapcev_WEB';

  changeTitle() {
    this.title = 'SIRCAP';
  }
  isLogged: boolean = false;
  isAdmin: boolean = false;
  username!: string;
  isTokenExpired: boolean = false;
  private tokenCheckInterval!: Subscription;

  constructor(
    private tokenService: TokenService,
    private router: Router,
    private dialog: MatDialog,
    private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer // Agregar DomSanitizer
  ) {
    this.matIconRegistry.addSvgIcon(
      `home`,
      this.domSanitizer.bypassSecurityTrustResourceUrl(`assets/icono/home.svg`)
    );
    this.matIconRegistry.addSvgIcon(
      `dashboard`,
      this.domSanitizer.bypassSecurityTrustResourceUrl(`assets/icono/graficas.svg`)
    );
    this.matIconRegistry.addSvgIcon(
      `persona`,
      this.domSanitizer.bypassSecurityTrustResourceUrl(`assets/icono/group.svg`)
    );

    this.matIconRegistry.addSvgIcon(
      `planes`,
      this.domSanitizer.bypassSecurityTrustResourceUrl(`assets/icono/history_edu.svg`)
    );
    this.matIconRegistry.addSvgIcon(
      `certificados`,
      this.domSanitizer.bypassSecurityTrustResourceUrl(`assets/icono/certificados.svg`)
    );
    this.matIconRegistry.addSvgIcon(
      `asignacion`,
      this.domSanitizer.bypassSecurityTrustResourceUrl(`assets/icono/priority.svg`)
    );
    this.matIconRegistry.addSvgIcon(
      `register`,
      this.domSanitizer.bypassSecurityTrustResourceUrl(`assets/icono/person_add.svg`)
    );
    this.matIconRegistry.addSvgIcon(
      `nuevo`,
      this.domSanitizer.bypassSecurityTrustResourceUrl(`assets/icono/home.svg`)
    );
  }
  ngOnInit(): void {
    this.isLogged = this.tokenService.isLogged();
    this.username = this.tokenService.getUsername();
    this.isTokenExpired = this.tokenService.isTokenExpired(); // Verificar si el token está vencido
    /*this.tokenCheckInterval = interval(600).subscribe(() => {
      this.checkTokenExpiration();
    });*/
  }

  ngOnDestroy(): void {
    // Detener la verificación del token al destruir el componente
    if (this.tokenCheckInterval) {
      this.tokenCheckInterval.unsubscribe();
    }
  }

  checkTokenExpiration(): void {
    this.isTokenExpired = this.tokenService.isTokenExpired();
    if (this.isTokenExpired) {
      // Realizar acciones adicionales si el token está vencido, como cerrar sesión
      this.tokenService.logOut(); // Asegúrate de que logOut cierre la sesión correctamente
      this.isLogged = false;
    }
  }


  @HostListener('window:beforeunload', ['$event'])
  unloadNotification($event: any): void {
    this.logOut();
  }

  logOut(): void {
    this.tokenService.logOut();
    this.router.navigate(['']);
    this.isLogged = false;
  }

  openLoginDialog(): void {
    const dialogRef = this.dialog.open(LoginDialogComponent, {
      width: '300px',
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      // El resultado indica si el inicio de sesión fue exitoso o no
      if (result) {
        this.isLogged = true;
        this.username = this.tokenService.getUsername();
      }
    });
  }

  openPerfilDialog(): void {
    const dialogRef = this.dialog.open(PerfilDialogComponent, {
      width: '20%',
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      // El resultado indica si el inicio de sesión fue exitoso o no
      if (result) {
        this.isLogged = true;
        this.username = this.tokenService.getUsername();
      }
    });
  }
  onHomeClick() {
    this.router.navigate(['']);
  }
  onDashboardClick() {
    this.router.navigate(['/dashboard']);

  }
  onPlanesClick() {
    this.router.navigate(['/planes']);
  }
  onPersonasClick() {
    this.router.navigate(['/personas']);
  }
  onCertificadosClick() {
    this.router.navigate(['/certificados']);
  }
  onInscripcionesClick() {
    this.router.navigate(['/inscripciones']);
  }
  onRegisterClick() {
    this.router.navigate(['/auth/register']);
  }
}
