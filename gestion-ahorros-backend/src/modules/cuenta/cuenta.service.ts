import { Injectable } from '@nestjs/common';
import { CreateCuentaDto } from './dto/create-cuenta.dto';
import { UpdateCuentaDto } from './dto/update-cuenta.dto';
import { Cuenta } from 'src/entities/cuenta.entity';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';

@Injectable()
export class CuentaService {

  constructor(
    @InjectRepository(Cuenta) private repo: Repository<Cuenta>
  ){}

  async create(user:any, createCuentaDto: CreateCuentaDto) {
    
    const create = this.repo.create({
      nombre: createCuentaDto.nombre,
      usuario: user.id,
    })

    return await this.repo.save(create);
  }

  async findAll(user:any) {
    console.log(user);
    
    return await this.repo.find({
      where: {
        usuario: {
          id: user.id
        }
      }
    })
  }

  async findOne(id: number) {

    return await this.repo.findOne({
      where: {
        id
      },
      relations: ['usuario']
    })
  }

  async update(id: number, updateCuentaDto: UpdateCuentaDto) {
    return await this.repo.update(id, updateCuentaDto);
  }

  remove(id: number) {
    return `This action removes a #${id} cuenta`;
  }
}
