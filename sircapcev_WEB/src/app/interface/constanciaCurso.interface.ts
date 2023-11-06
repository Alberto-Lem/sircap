export interface ConstanciaCurso {
    id?:string;
    gradoPolicial: string;
    nombres: string;
    apellidos: string;
    nip: string;
    asignaturas: string [];
    aprobacion: string;
    fechaCreacion: Date
    fechaModificacion: Date
    descripcion: string;
    documentoRespaldo: string;
    estado: string;
    usuario: string;
}
