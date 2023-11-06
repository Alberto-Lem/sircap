import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-graficas',
  templateUrl: './graficas.component.html',
  styleUrls: ['./graficas.component.css']
})
export class GraficasComponent implements OnInit {
 
    graficos: any = { 
    'grafico1': {
      'leyenda': 'Capacitados por años, hombres y mujeres'
    },
    'grafico2': {
      'leyenda': 'Capacitados por genero'
    },
    'grafico3': {
      'leyenda': 'Capacitado por planes, generos y años'
    },
    'grafico4': {
      'leyenda': 'Por Subdirección'
    },
    'grafico5': {
      'leyenda': 'Capacitados por grado policial, por año'
    }
  };
   
  constructor() { }

  ngOnInit(): void {
  }

}
