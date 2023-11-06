import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/service/auth.service';
import { TokenService } from 'src/app/service/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-perfil-dialog',
  templateUrl: './perfil-dialog.component.html',
  styleUrls: ['./perfil-dialog.component.css']
})
export class PerfilDialogComponent implements OnInit {
  profileImageUrl: string = 'assets/no-image-perfil.png';
  isLogged: boolean = true;
  username!: string;
  oldUsername: string = '';
  newUsername: string = '';
  oldPassword: string = '';
  newPassword: string = '';
  confirmPassword: string = '';
  showOldPassword = false;
  showNewPassword = false;
  showConfirmPassword = false;
  isUsernameInvalid = false;
  isPasswordTooLong = false;
  isPasswordTooShort = false;
  loginError = '';
  showProgressBar: boolean = false;

  constructor(
    private router: Router,
    private authService: AuthService,
    private tokenService: TokenService,
    private toast: ToastrService,
    public dialogRef: MatDialogRef<PerfilDialogComponent>
  ) { }
  ngOnInit(): void {
    this.username = this.tokenService.getUsername();
    this.oldUsername = this.username;
    this.isLogged = this.tokenService.isLogged();
  }

  onSubmit(form: NgForm): void {
    if (form.valid) {
      this.showProgressBar = true;
      setTimeout(() => {
        this.showProgressBar = false;
      }, 2000);
      this.onSaveChanges(); // Mover esto aquí
    }
  }


  onSaveChanges(): void {
    console.log('Old Username:', this.oldUsername);
    console.log('New Username:', this.newUsername);
    console.log('Old Password:', this.oldPassword);
    console.log('New Password:', this.newPassword);
    console.log('Confirm Password:', this.confirmPassword);

    if (this.newPassword !== this.confirmPassword) {
      this.toast.error('Las contraseñas no coinciden.', 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
      return;
    }

    // Crear un DTO (Data Transfer Object) para enviar la información al servidor
    const updateProfile = {
      oldUsername: this.oldUsername,
      newUsername: this.newUsername,
      oldPassword: this.oldPassword,
      newPassword: this.newPassword,
      confirmPassword: this.confirmPassword
    };
    console.log('Update Profile DTO:', updateProfile);

    // Llamar al servicio AuthService para realizar el cambio
    this.authService.changePassword(updateProfile).subscribe({
      next: () => {
        this.toast.success('Perfil actualizado con éxito.', 'Éxito', { timeOut: 3000, positionClass: 'toast-top-center' });
        this.username = this.newUsername;
        this.tokenService.logOut(); // Asegúrate de que logOut cierre la sesión correctamente
        this.isLogged = false;
        location.reload();
        this.dialogRef.close(true);
      },
      error: (err) => {
        this.loginError = err.error.message;
        this.toast.error(this.loginError, 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
      }
    });
  }


  logOut(): void {
    this.tokenService.logOut();
    this.router.navigate(['']);
    this.isLogged = false;
  }


  toggleOldPasswordVisibility(): void {
    this.showOldPassword = !this.showOldPassword;
  }

  toggleNewPasswordVisibility(): void {
    this.showNewPassword = !this.showNewPassword;
  }

  toggleConfirmPasswordVisibility(): void {
    this.showConfirmPassword = !this.showConfirmPassword;
  }

  checkUsernameValidity(): void {
    this.isUsernameInvalid = !/^\d{5}[ap]$/.test(this.newUsername); // Validando el nuevo nombre de usuario
    if (this.newUsername.length > 6) {
      this.newUsername = this.newUsername.slice(0, 6);
    }
  }

  checkPasswordLength(): void {
    const minLength = 5;
    this.isPasswordTooShort = this.newPassword.length < minLength;
  }

  onCancel(): void {
    this.dialogRef.close(false);
  }

}
