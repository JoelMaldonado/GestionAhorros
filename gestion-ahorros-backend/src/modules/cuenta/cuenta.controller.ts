import { Controller, Get, Post, Body, Patch, Param, Delete, UseGuards, Req, HttpException, HttpStatus, Res } from '@nestjs/common';
import { CuentaService } from './cuenta.service';
import { CreateCuentaDto } from './dto/create-cuenta.dto';
import { UpdateCuentaDto } from './dto/update-cuenta.dto';
import { ApiBearerAuth, ApiTags } from '@nestjs/swagger';
import { JwtAuthGuard } from 'src/utils/jwt-auth.guard';
import { Request, Response } from 'express';

@ApiBearerAuth()
@ApiTags("Cuenta")
@Controller('cuenta')
export class CuentaController {
  constructor(private readonly cuentaService: CuentaService) { }

  @UseGuards(JwtAuthGuard)
  @Post()
  create(@Body() createCuentaDto: CreateCuentaDto, @Req() req: Request) {
    const user = req.user;
    return this.cuentaService.create(user, createCuentaDto);
  }

  @UseGuards(JwtAuthGuard)
  @Get()
  async findAll(@Req() req: Request, @Res() res: Response) {
    try {
      const data = await this.cuentaService.findAll(req.user);
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
  async findOne(@Param('id') id: string) {
    try {
      return await this.cuentaService.findOne(+id);
    } catch (error) {
      throw new HttpException('No se encontro el usuario', HttpStatus.FORBIDDEN)
    }
  }

  @UseGuards(JwtAuthGuard)
  @Patch(':id')
  async update(@Param('id') id: string, @Body() updateCuentaDto: UpdateCuentaDto) {
    return await this.cuentaService.update(+id, updateCuentaDto);
  }

  @UseGuards(JwtAuthGuard)
  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.cuentaService.remove(+id);
  }
}
