import { Module } from '@nestjs/common';
import { CuentaService } from './cuenta.service';
import { CuentaController } from './cuenta.controller';
import { Cuenta } from 'src/entities/cuenta.entity';
import { TypeOrmModule } from '@nestjs/typeorm';

@Module({
  imports: [
    TypeOrmModule.forFeature([Cuenta])
  ],
  controllers: [CuentaController],
  providers: [CuentaService],
})
export class CuentaModule {}
