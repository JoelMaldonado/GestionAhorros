import { Controller, Get, Post, Body, Patch, Param, Delete, UseGuards, UseInterceptors } from '@nestjs/common';
import { TransaccionService } from './transaccion.service';
import { CreateTransaccionDto } from './dto/create-transaccion.dto';
import { UpdateTransaccionDto } from './dto/update-transaccion.dto';
import { ApiBearerAuth, ApiTags } from '@nestjs/swagger';
import { JwtAuthGuard } from 'src/utils/jwt-auth.guard';
import { TransformResponseInterceptor } from 'src/interceptors/transform_response_interceptor';

@ApiBearerAuth()
@ApiTags("Transacción")
@Controller('transaccion')
export class TransaccionController {
  constructor(private readonly transaccionService: TransaccionService) {}

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Post()
  create(@Body() createTransaccionDto: CreateTransaccionDto) {
    return this.transaccionService.create(createTransaccionDto);
  }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Get()
  findAll() {
    return this.transaccionService.findAll();
  }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.transaccionService.findOne(+id);
  }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Patch(':id')
  update(@Param('id') id: string, @Body() updateTransaccionDto: UpdateTransaccionDto) {
    return this.transaccionService.update(+id, updateTransaccionDto);
  }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.transaccionService.remove(+id);
  }
}
