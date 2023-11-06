import { Time } from "@angular/common";

export interface Inscripciones {
  id?: string;
  nip: string;
  noPlan: string;
  fechaInscripcion?: Date;
  horaInscripcion?: Time;
  fechaModificacion?: Date;
  hechoPor?: string;
  ultimaModificacionPor?: string;
  tipo?: string;
  estado: string;
  observaciones: string;
  persona?: {
    nombres: string;
    apellidos: string;
  };
  plan?: {
    nombrePlan: string;
  };
}
