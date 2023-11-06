import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MaterialModule } from '../material/material.module';
import { ListadopersonaComponent } from './page/listadopersona/listadopersona.component';
import { PersonaRoutinModule } from './persona-routing.module';
import { PersonasComponent } from './page/personas/personas.component';
import { IngresarpersonaComponent } from './page/ingresarpersona/ingresarpersona.component';
import { DetailPersonaComponent } from './page/detail-persona/detail-persona.component';
@NgModule({
  declarations: [
    ListadopersonaComponent,
    IngresarpersonaComponent,
    PersonasComponent,
    DetailPersonaComponent,
  ],
  imports: [
    CommonModule,
    PersonaRoutinModule,
    MaterialModule
  ],
})
export class PersonaModule { }
