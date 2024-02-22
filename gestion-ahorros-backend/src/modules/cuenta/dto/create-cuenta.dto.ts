import { IsNotEmpty } from "class-validator";

export class CreateCuentaDto {
    

    @IsNotEmpty()
    nombre: string;
    
}
