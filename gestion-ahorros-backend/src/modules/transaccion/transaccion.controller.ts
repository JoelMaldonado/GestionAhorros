import { Controller, Get, Post, Body, Patch, Param, Delete, UseGuards, UseInterceptors, UploadedFile, Query, Res } from '@nestjs/common';
import { TransaccionService } from './transaccion.service';
import { CreateTransaccionDto } from './dto/create-transaccion.dto';
import { UpdateTransaccionDto } from './dto/update-transaccion.dto';
import { ApiBearerAuth, ApiTags } from '@nestjs/swagger';
import { JwtAuthGuard } from 'src/utils/jwt-auth.guard';
import { TransformResponseInterceptor } from 'src/interceptors/transform_response_interceptor';
import { FileInterceptor } from '@nestjs/platform-express';


@ApiBearerAuth()
@ApiTags("Transacción")
@Controller('transaccion')
export class TransaccionController {
  constructor(private readonly transaccionService: TransaccionService) {}

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Post()
  @UseInterceptors(FileInterceptor('imagen'))
  create(@UploadedFile() imagen) {
    const fs = require('fs');
    fs.writeFileSync(`assets/images/transacs/${imagen.originalname}`, imagen.buffer);
    return { mensaje: 'Imagen subida exitosamente' };
  }

  @UseInterceptors(TransformResponseInterceptor)
  @Get()
  findAll(@Res() res, @Query('url') url: string) {
   return this.transaccionService.findAll
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
