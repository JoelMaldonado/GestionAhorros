import { Controller, Get, Post, Body, Patch, Param, Delete, UseGuards } from '@nestjs/common';
import { TransaccionService } from './transaccion.service';
import { CreateTransaccionDto } from './dto/create-transaccion.dto';
import { UpdateTransaccionDto } from './dto/update-transaccion.dto';
import { ApiBearerAuth, ApiTags } from '@nestjs/swagger';
import { JwtAuthGuard } from 'src/utils/jwt-auth.guard';

@ApiBearerAuth()
@ApiTags("Transacción")
@Controller('transaccion')
export class TransaccionController {
  constructor(private readonly transaccionService: TransaccionService) {}

  @UseGuards(JwtAuthGuard)
  @Post()
  create(@Body() createTransaccionDto: CreateTransaccionDto) {
    return this.transaccionService.create(createTransaccionDto);
  }

  @UseGuards(JwtAuthGuard)
  @Get()
  findAll() {
    return this.transaccionService.findAll();
  }

  @UseGuards(JwtAuthGuard)
  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.transaccionService.findOne(+id);
  }

  @UseGuards(JwtAuthGuard)
  @Patch(':id')
  update(@Param('id') id: string, @Body() updateTransaccionDto: UpdateTransaccionDto) {
    return this.transaccionService.update(+id, updateTransaccionDto);
  }

  @UseGuards(JwtAuthGuard)
  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.transaccionService.remove(+id);
  }
}
