import { IsEnum, IsNotEmpty, IsNumber, isEnum } from "class-validator";

export class CreateCategoriaDto {



    @IsNotEmpty()
    nombre: string;

    @IsEnum(['Gasto', 'Ingreso'])
    tipo: 'Gasto' | 'Ingreso';

    @IsNumber()
    color: number;

    @IsNumber()
    icono: number;

    usuario:any

}
