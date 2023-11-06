import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { CicloRoutinModule } from './ciclo-routin.module';
import { CicloComponent } from './page/ciclo/ciclo.component';

import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    CicloComponent,
  ],
  imports: [
    HttpClientModule,
    MatIconModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    CommonModule,
    CicloRoutinModule
  ],
})
export class CicloModule { }
