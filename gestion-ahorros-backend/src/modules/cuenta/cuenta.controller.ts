import { Controller, Get, Post, Body, Patch, Param, Delete, UseGuards, Req, HttpException, HttpStatus, Res, UseInterceptors } from '@nestjs/common';
import { CuentaService } from './cuenta.service';
import { CreateCuentaDto } from './dto/create-cuenta.dto';
import { UpdateCuentaDto } from './dto/update-cuenta.dto';
import { ApiBearerAuth, ApiTags } from '@nestjs/swagger';
import { JwtAuthGuard } from 'src/utils/jwt-auth.guard';
import { Request, Response } from 'express';
import { TransformResponseInterceptor } from 'src/interceptors/transform_response_interceptor';

@ApiBearerAuth()
@ApiTags("Cuenta")
@Controller('cuenta')
export class CuentaController {
  constructor(private readonly cuentaService: CuentaService) { }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Post()
  create(@Body() createCuentaDto: CreateCuentaDto, @Req() req) {
    createCuentaDto.usuario = req.user.id;
    return this.cuentaService.create(createCuentaDto);
  }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Get()
  async findAll(@Req() req: Request) {
    const data = await this.cuentaService.findAll(req.user);
    return data
  }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Get(':id')
  async findOne(@Param('id') id: string) {
    try {
      return await this.cuentaService.findOne(+id);
    } catch (error) {
      throw new HttpException('No se encontro el usuario', HttpStatus.FORBIDDEN)
    }
  }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Patch(':id')
  async update(@Param('id') id: string, @Body() updateCuentaDto: UpdateCuentaDto) {
    return await this.cuentaService.update(+id, updateCuentaDto);
  }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.cuentaService.remove(+id);
  }
}
