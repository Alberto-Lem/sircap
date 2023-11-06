import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { PlanesService } from 'src/app/service/planes.service';
import { Planes } from '../../planesinterface/planes.interface';

@Component({
  selector: 'app-detail-planes',
  templateUrl: './detail-planes.component.html',
  styleUrls: ['./detail-planes.component.css'],
})
export class DetailPlanesComponent implements OnInit, OnDestroy {
  planes: Planes | null = null;
  private subscription: Subscription | null = null;

  constructor(
    private route: ActivatedRoute,
    private planesService: PlanesService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const planesId = params['id'];

      // Llama al servicio para obtener los detalles del planes
      this.subscription = this.planesService.detail(planesId).subscribe({
        next: (data: Planes) => {
          this.planes = data;
        },
        error: (error: any) => {
          console.error('Error al obtener los detalles del planes:', error);
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
