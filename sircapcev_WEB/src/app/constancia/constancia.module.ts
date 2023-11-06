import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CrearconstanciaComponent } from './page/crearconstancia/crearconstancia.component';
import { ConstanciaComponent } from './page/constancia/constancia.component';
import { ConstanciaRoutinModule } from './constancia-routin.module';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    CrearconstanciaComponent,
    ConstanciaComponent
  ],
  imports: [
    MatIconModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    CommonModule,
    ConstanciaRoutinModule
  ]
})
export class ConstanciaModule { }
