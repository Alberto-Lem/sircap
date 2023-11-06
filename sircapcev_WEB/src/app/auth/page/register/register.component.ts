import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CreateUserDto } from 'src/app/interface/create-user-dto';
import { AuthService } from 'src/app/service/auth.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  username!: string;
  email!: string;
  password!: string;

  constructor(
    private authService: AuthService,
    private tokenService: TokenService,
    private toast: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  onRegister(): void {
    const dto: CreateUserDto = { username: this.username, email: this.email, password: this.password };
    
    this.authService.register(dto).subscribe({
      next: (data) => {
        this.toast.success(data.message, 'OK', { timeOut: 3000, positionClass: 'toast-top-center' });
        this.router.navigate(['']);
      },
      error: (err) => {
        this.toast.error(err.error.message, 'Error', { timeOut: 3000, positionClass: 'toast-top-center' });
      }
    });
  }
}
