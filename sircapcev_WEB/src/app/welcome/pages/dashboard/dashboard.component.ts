import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IpService } from 'src/app/service/ip.service';
import { PersonaService } from 'src/app/service/persona.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  ipAddress!: string;
  isLogged: boolean = false;
  username!: string;
  nombre!: string;

  constructor(
    private tokenService: TokenService,
    private router: Router,
    private ipService: IpService,
    private personaService: PersonaService
  ) { }

  ngOnInit(): void {
    this.isLogged = this.tokenService.isLogged();
    this.ipService.getIpAddress().then(ip => this.ipAddress = ip);
    this.username = this.tokenService.getUsername();
  }

  get greeting(): string {
    const currentHour = new Date().getHours();
    let greetingText = '';

    if (currentHour >= 5 && currentHour < 12) {
      greetingText = 'Buenos días';
    } else if (currentHour >= 12 && currentHour < 18) {
      greetingText = 'Buenas tardes';
    } else {
      greetingText = 'Buenas noches';
    }

    return `${greetingText}, ${this.username}`;
  }
}
