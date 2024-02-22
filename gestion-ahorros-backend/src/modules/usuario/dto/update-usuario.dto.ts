import { IsEmail, IsOptional, MinLength } from "class-validator"

export class UpdateUsuarioDto {

    @IsOptional()
    nombre?:string

    @IsOptional()
    apellido?: string

    @IsOptional()
    @IsEmail()
    correo?:string
    
}