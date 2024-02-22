import { IsEnum, IsNotEmpty, isEnum } from "class-validator";

export class CreateCategoriaDto {



    @IsNotEmpty()
    nombre: string;

    @IsEnum(['Gasto', 'Ingreso'])
    tipo: 'Gasto' | 'Ingreso';

    usuario:any

}
