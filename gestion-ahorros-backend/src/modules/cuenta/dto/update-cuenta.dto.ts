import { PartialType } from '@nestjs/mapped-types';
import { CreateCuentaDto } from './create-cuenta.dto';
import { IsNotEmpty } from 'class-validator';

export class UpdateCuentaDto extends PartialType(CreateCuentaDto) {

    @IsNotEmpty()
    nombre: string;
}
