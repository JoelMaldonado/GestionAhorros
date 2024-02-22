import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { LoginDto } from './dto/login.dto';
import { UsuarioService } from '../usuario/usuario.service';
import { JwtService } from '@nestjs/jwt';
import { compare, hash } from 'bcrypt';
import { RegisterDto } from './dto/register.dto';
import { Usuario } from 'src/entities/usuario.entity';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { log } from 'console';

@Injectable()
export class AuthService {

  constructor(
    @InjectRepository(Usuario) private userRepository: Repository<Usuario>,
    private usuarioService: UsuarioService,
    private jwtService: JwtService
  ) { }

  async login(data: LoginDto) {

    const user = await this.usuarioService.findUserByEmail(data.correo);

    if (!user) {
      throw new Error('Usuario no encontrado');
    }

    const checkPass = await compare(data.password, user.clave);

    if (!checkPass){
      throw new Error('Contraseña incorrecta');
    }
    const { id, nombre, correo } = user;
    const payload = { id, nombre, correo};
    const token = this.jwtService.sign(payload);

    return {
      token: token
    };
  }

  async createUser(data: RegisterDto) {
    
      const userFound = await this.usuarioService.findUserByEmail(data.correo)

      if (userFound) {
          throw new Error("Correo ya registrado")
      }

      const claveHash = await hash(data.clave, 10)
      data.clave = claveHash


      const userCreate = this.userRepository.create(data)
      return await this.userRepository.save(userCreate)
  }
}
