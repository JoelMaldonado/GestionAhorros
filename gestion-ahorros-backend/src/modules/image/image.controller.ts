import { Controller, Delete, Get, Post, Query, Res, UploadedFile, UseGuards, UseInterceptors } from '@nestjs/common';
import { ImageService } from './image.service';
import * as path from 'path';
import * as fs from 'fs';
import { FileInterceptor } from '@nestjs/platform-express';
import { TransformResponseInterceptor } from 'src/interceptors/transform_response_interceptor';
import { JwtAuthGuard } from 'src/utils/jwt-auth.guard';

@Controller('image')
export class ImageController {
  constructor(private readonly imageService: ImageService) { }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Post()
  @UseInterceptors(FileInterceptor('imagen'))
  create(@UploadedFile() imagen) {
    const fs = require('fs');
    const nombreArchivo = new Date().getTime() + '.jpg';
    fs.writeFileSync(`assets/images/transacs/${nombreArchivo}`, imagen.buffer);
    return nombreArchivo;
  }

  @UseInterceptors(TransformResponseInterceptor)
  @Get()
  find(@Res() res, @Query('url') url: string) {

    if (!url) {
      throw new Error('La URL de la imagen es requerida');
    }
    const rutaImagen = path.resolve('assets/images/transacs/', url);
    if (fs.existsSync(rutaImagen)) {
      res.sendFile(rutaImagen);
    } else {
      throw new Error('La imagen no existe');
    }
  }

  @UseInterceptors(TransformResponseInterceptor)
  @Delete()
  async eliminarImagen(@Query('url') url: string) {
    if (!url) {
      throw new Error('La URL de la imagen es requerida');
    }
    const rutaImagen = path.resolve('assets/images/transacs/', url);
    if (fs.existsSync(rutaImagen)) {
      fs.unlinkSync(rutaImagen);
      return "Imagen eliminada exitosamente"
    } else {
      throw new Error('La imagen no existe');
    }
  }
}
