import { IsEmail, IsOptional, MinLength } from "class-validator"


export class RegisterDto {

    @IsOptional()
    nombre?:string

    @IsOptional()
    apellido?: string

    @IsEmail()
    correo:string

    @MinLength(4)
    clave:string
    
}