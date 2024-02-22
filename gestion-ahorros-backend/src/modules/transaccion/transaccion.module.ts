import { Module } from '@nestjs/common';
import { TransaccionService } from './transaccion.service';
import { TransaccionController } from './transaccion.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Transaccion } from 'src/entities/transaccion.entity';

@Module({
  imports: [
    TypeOrmModule.forFeature([Transaccion])
  ],
  controllers: [TransaccionController],
  providers: [TransaccionService],
})
export class TransaccionModule {}
