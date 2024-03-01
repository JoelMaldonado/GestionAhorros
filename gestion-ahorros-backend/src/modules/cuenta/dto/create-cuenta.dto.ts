import { IsNotEmpty, IsNumber } from "class-validator";

export class CreateCuentaDto {
    

    @IsNotEmpty()
    nombre: string;
    
    @IsNumber()
    color: number;

    @IsNumber()
    icono: number;

    usuario:any;
    
}
