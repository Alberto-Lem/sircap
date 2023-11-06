export interface Planes {
    id?: string;
    noPlan: string;
    nombrePlan: string;
    cantidadParticipante?: number;
    estado: string;
    fechaCreacion?: Date ;
    fechaModificacion?: Date;
    fechaInicio: Date
    fechaFinalizacion: Date;
    modalidad: string;
    poblacion: string;
    encargado: string;
    lugarEjecucion: string;
    comentario?: string;
}

