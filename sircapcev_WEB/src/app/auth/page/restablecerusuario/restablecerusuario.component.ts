import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-restablecerusuario',
  templateUrl: './restablecerusuario.component.html',
  styleUrls: ['./restablecerusuario.component.css']
})
export class RestablecerusuarioComponent implements OnInit {

  Roles: any = ['Admin', 'Author', 'Reader'];
  constructor() { }

  ngOnInit(): void {
  }

}
