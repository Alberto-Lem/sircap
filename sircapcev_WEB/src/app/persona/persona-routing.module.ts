import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { IngresarpersonaComponent } from "./page/ingresarpersona/ingresarpersona.component";
import { ListadopersonaComponent } from "./page/listadopersona/listadopersona.component";
import { PersonasComponent } from "./page/personas/personas.component";
import { DetailPersonaComponent } from "./page/detail-persona/detail-persona.component";

const routes: Routes = [
  {
    path: '',
    component: PersonasComponent,
    children: [
      {
        path: 'listadopersona',
        component: ListadopersonaComponent,
      },
      {
        path: 'listadopersona/:id',
        component: DetailPersonaComponent,
      },
      {
        path: 'ingresarpersona',
        component: IngresarpersonaComponent,
        pathMatch: 'full'
      },
      {
        path: 'editar/:id',
        component: IngresarpersonaComponent,
      },
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'listadopersona',
      }
    ]
  },
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class PersonaRoutinModule { }
