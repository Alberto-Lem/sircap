import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Persona } from 'src/app/interface/persona.interface';
import { PersonaService } from 'src/app/service/persona.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-detail-persona',
  templateUrl: './detail-persona.component.html',
  styleUrls: ['./detail-persona.component.css']
})
export class DetailPersonaComponent implements OnInit, OnDestroy {
  persona: Persona | null = null;
  private subscription: Subscription | null = null;

  constructor(
    private router: ActivatedRoute,
    private personaService: PersonaService
  ) {}

  ngOnInit(): void {
    this.router.params.subscribe((params) => {
      const personaId = params['id'];
      this.subscription = this.personaService.detail(personaId).subscribe({
        next: (data: Persona) => {
          this.persona = data;
        },
        error: (error: any) => {
          console.error('Error al obtener los detalles de la persona:', error);
        }
      });
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
