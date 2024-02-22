import { Body, Controller, Delete, Get, HttpException, Param, ParseIntPipe, Patch, UseGuards } from '@nestjs/common';
import { UsuarioService } from './usuario.service';
import { ApiBearerAuth, ApiTags } from '@nestjs/swagger';
import { UpdateUsuarioDto } from './dto/update-usuario.dto';
import { JwtAuthGuard } from 'src/utils/jwt-auth.guard';

@ApiBearerAuth()
@ApiTags("Usuario")
@Controller('usuario')
export class UsuarioController {

  constructor(private readonly usuarioService: UsuarioService) { }

  @UseGuards(JwtAuthGuard)
  @Get()
  async getAllUsers() {
    return await this.usuarioService.getAllUsers();
  }

  @UseGuards(JwtAuthGuard)
  @Patch(':id')
  async updateUser(@Param('id', ParseIntPipe) id: number, @Body() user: UpdateUsuarioDto) {
    try {
      return await this.usuarioService.updateUser(id, user)
    } catch (error) {
      
      if (error instanceof HttpException) {
        throw error;
      } else {
        return { message: 'No se pudo actualizar usuario', error: error.message };
      }
    }
  }

  @UseGuards(JwtAuthGuard)
  @Delete(':id')
  async deleteUser(@Param('id', ParseIntPipe) id: number) {
    try {
      return await this.usuarioService.deleteUser(id)
    } catch (error) {
      if (error instanceof HttpException) {
        throw error;
      } else {
        return { message: 'No se pudo eliminar usuario', error: error.message };
      }
    }
  }

}
