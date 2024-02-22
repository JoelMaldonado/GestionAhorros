import { Injectable } from '@nestjs/common';
import { CreateTransaccionDto } from './dto/create-transaccion.dto';
import { UpdateTransaccionDto } from './dto/update-transaccion.dto';
import { Transaccion } from 'src/entities/transaccion.entity';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';

@Injectable()
export class TransaccionService {

  constructor(
    @InjectRepository(Transaccion) private repo: Repository<Transaccion>
  ){}

  create(createTransaccionDto: CreateTransaccionDto) {
    return 'This action adds a new transaccion';
  }

  findAll() {
    return `This action returns all transaccion`;
  }

  findOne(id: number) {
    return `This action returns a #${id} transaccion`;
  }

  update(id: number, updateTransaccionDto: UpdateTransaccionDto) {
    return `This action updates a #${id} transaccion`;
  }

  remove(id: number) {
    return `This action removes a #${id} transaccion`;
  }
}

