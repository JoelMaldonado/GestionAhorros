import { Controller, Get, Post, Body, Patch, Param, Delete, UseGuards, Req, Res } from '@nestjs/common';
import { CategoriaService } from './categoria.service';
import { CreateCategoriaDto } from './dto/create-categoria.dto';
import { UpdateCategoriaDto } from './dto/update-categoria.dto';
import { ApiBearerAuth, ApiTags } from '@nestjs/swagger';
import { JwtAuthGuard } from 'src/utils/jwt-auth.guard';
import { Response } from 'express';

@ApiBearerAuth()
@ApiTags("Categoria")
@Controller('categoria')
export class CategoriaController {
  constructor(private readonly categoriaService: CategoriaService) { }

  @UseGuards(JwtAuthGuard)
  @Post()
  create(@Body() createCategoriaDto: CreateCategoriaDto, @Req() req) {
    createCategoriaDto.usuario = req.user.id;
    return this.categoriaService.create(createCategoriaDto);
  }

  @UseGuards(JwtAuthGuard)
  @Get()
  async findAll(@Req() req, @Res() res: Response) {
    try {
      const data = await this.categoriaService.findAll(req.user.id);
      return res.json({
        success: true,
        data: data
      })
    } catch (error) {
      return res.json({
        success: false,
        message: error.message
      })
    }

  }

  @UseGuards(JwtAuthGuard)
  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.categoriaService.findOne(+id);
  }

  @UseGuards(JwtAuthGuard)
  @Patch(':id')
  update(@Param('id') id: string, @Body() updateCategoriaDto: UpdateCategoriaDto) {
    return this.categoriaService.update(+id, updateCategoriaDto);
  }

  @UseGuards(JwtAuthGuard)
  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.categoriaService.remove(+id);
  }
}
