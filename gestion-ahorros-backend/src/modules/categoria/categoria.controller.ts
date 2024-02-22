import { Controller, Get, Post, Body, Patch, Param, Delete, UseGuards, Req, Res, UseInterceptors } from '@nestjs/common';
import { CategoriaService } from './categoria.service';
import { CreateCategoriaDto } from './dto/create-categoria.dto';
import { UpdateCategoriaDto } from './dto/update-categoria.dto';
import { ApiBearerAuth, ApiTags } from '@nestjs/swagger';
import { JwtAuthGuard } from 'src/utils/jwt-auth.guard';
import { Response } from 'express';
import { TransformResponseInterceptor } from 'src/interceptors/transform_response_interceptor';

@ApiBearerAuth()
@ApiTags("Categoria")
@Controller('categoria')
export class CategoriaController {
  constructor(private readonly categoriaService: CategoriaService) { }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Post()
  create(@Body() createCategoriaDto: CreateCategoriaDto, @Req() req) {
    createCategoriaDto.usuario = req.user.id;
    return this.categoriaService.create(createCategoriaDto);
  }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Get()
  async findAll(@Req() req) {
    const data = await this.categoriaService.findAll(req.user.id);
    return data
  }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.categoriaService.findOne(+id);
  }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Patch(':id')
  update(@Param('id') id: string, @Body() updateCategoriaDto: UpdateCategoriaDto) {
    return this.categoriaService.update(+id, updateCategoriaDto);
  }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.categoriaService.remove(+id);
  }
}
