import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { LoginUserDto } from 'src/app/interface/login-user-dto';
import { AuthService } from 'src/app/service/auth.service';
import { TokenService } from 'src/app/service/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.css']
})
export class LoginDialogComponent implements OnInit {
  username: string = '';
  password: string = '';
  showPassword = false;
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
    public dialogRef: MatDialogRef<LoginDialogComponent>
  ) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm): void {
    if (form.valid) {
      this.showProgressBar = true;
      setTimeout(() => {
        this.showProgressBar = false;
      }, 2000);
    }
    this.onLogin();
  }

  onLogin(): void {
    const dto: LoginUserDto = { username: this.username, password: this.password };

    this.authService.login(dto).subscribe({
      next: (data) => {
        this.tokenService.setToken(data.token);
        this.dialogRef.close(true);
      },
      error: (err) => {
        if (err.error && err.error.message) {
          this.toast.error(err.error.message, 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
        } else {
          this.toast.error('Ha ocurrido un error durante el inicio de sesión.', 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
        }
      }
    });
  }

  onCancel(): void {
    this.dialogRef.close(false); // Cierra el diálogo y devuelve 'false' para indicar que el inicio de sesión fue cancelado
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  checkUsernameValidity(): void {
    this.isUsernameInvalid = !/^\d{5}[ap]$/.test(this.username);
    if (this.username.length > 6) {
      this.username = this.username.slice(0, 6);
    }
  }  

  checkPasswordLength(): void {
    const minLength = 5;
    this.isPasswordTooShort = this.password.length < minLength;
  }
  


  onForgotPassword(): void {
    // Por ejemplo, podrías abrir otro diálogo para el restablecimiento de contraseña
  }
}
