import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Usuario } from 'src/entities/usuario.entity';
import { Repository } from 'typeorm';
import { UpdateUsuarioDto } from './dto/update-usuario.dto';

@Injectable()
export class UsuarioService {

    constructor(
        @InjectRepository(Usuario) private userRepository: Repository<Usuario>
    ) { }

    async getAllUsers() {
        return await this.userRepository.find();
    }

    async findUser(id: number) {
        return await this.userRepository.findOne({
            where: {
                id
            }
        })
    }

    async findUserByEmail(email: string) {
        return await this.userRepository.findOne({
            where: {
                correo: email
            }
        })
    }

    async updateUser(id: number, data: UpdateUsuarioDto) {
        const userFound = await this.findUser(id)

        if (!userFound) {
            throw new HttpException('Usuario no encontrado', HttpStatus.NOT_FOUND)
        }

        return await this.userRepository.update({ id }, data)
    }

    async deleteUser(id: number) {
        const userFound = await this.findUser(id)

        if (!userFound) {
            throw new HttpException('Usuario no encontrado', HttpStatus.NOT_FOUND)
        }

        return await this.userRepository.update({ id }, { activo: !userFound.activo })
    }

}