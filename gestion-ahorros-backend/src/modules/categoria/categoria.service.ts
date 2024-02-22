import { Injectable } from '@nestjs/common';
import { CreateCategoriaDto } from './dto/create-categoria.dto';
import { UpdateCategoriaDto } from './dto/update-categoria.dto';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Categoria } from 'src/entities/categoria.entity';

@Injectable()
export class CategoriaService {
  constructor(
    @InjectRepository(Categoria) private repo: Repository<Categoria>
  ){}
  
  async create(createCategoriaDto: CreateCategoriaDto) {
    const create = this.repo.create(createCategoriaDto)
    return await this.repo.save(create);
  }

  async findAll(userId:number) {
    return await this.repo.find({
      where: {
        usuario: {
          id: userId
        }
      }
    })
  }

  findOne(id: number) {
    return `This action returns a #${id} categoria`;
  }

  update(id: number, updateCategoriaDto: UpdateCategoriaDto) {
    return `This action updates a #${id} categoria`;
  }

  remove(id: number) {
    return `This action removes a #${id} categoria`;
  }
}
