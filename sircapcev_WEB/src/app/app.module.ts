import { APP_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GraficaHorizontalComponent } from './welcome/components/grafica-horizontal/grafica-horizontal.component';
import { GraficoLineasComponent } from './welcome/components/grafico-lineas/grafico-lineas.component';
import { DashboardComponent } from './welcome/pages/dashboard/dashboard.component';
import { GraficoBarraComponent } from './welcome/components/barras/grafico-barra.component';
import { GraficoDonaComponent } from './welcome/components/donas/grafico-dona.component';
import { GraficasComponent } from './welcome/components/graficas/graficas.component';
import { HomeComponent } from './welcome/pages/home/home.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SharedModule } from './shared/shared.module';
import { MaterialModule } from './material/material.module';
import { AuthInterceptor } from './helpers/auth.interceptor';
import { GraficaDona1Component } from './welcome/components/grafica-dona1/grafica-dona1.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    GraficasComponent,
    GraficoDonaComponent,
    GraficaDona1Component,
    GraficoBarraComponent,
    DashboardComponent,
    GraficoLineasComponent,
    GraficaHorizontalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    SharedModule,
    NgxChartsModule,
    MaterialModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: APP_ID, useValue: 'serverApp' },
  ],
  bootstrap: [AppComponent],
  exports: [MaterialModule]
})
export class AppModule { }
