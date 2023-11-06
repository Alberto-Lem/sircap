    export interface Persona {
        no?: number; //Para contar los registros
        id?: string;
        nip: string;
        gradoPolicial: string;
        nombres: string;
        apellidos: string;
        genero: string;
        dpi: string;
        nit: string;
        noAfiliacionIgss?: number
        renglonPresupuestario: string;
        fechaDeNacimiento: Date;
        edad?: number;
        direccionResidencial: string;
        lugarDeServicio: string;
        destinoActual?: string;
        dependenciaDondeLabora: string;
        telefono: string;
        correoElectronicoPersonal: string;
        correoElectronicoInstitucional?: string;
        grupoEtnico: string;
        escolaridad: string;
        tituloObtenido: string;
        numeroColegiadoActivo?: string;
        idiomasQueDomina: string [];
        estudiaActualmente?: string;
        nombreCarrera?: string;
        otrosEstudiosRealizados?: string;
    }


