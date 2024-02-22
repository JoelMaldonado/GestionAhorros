import { Controller, Get, Post, Body, Patch, Param, Delete, UseGuards, Request, Res, HttpStatus, HttpException } from '@nestjs/common';
import { AuthService } from './auth.service';
import { LoginDto } from './dto/login.dto';
import { ApiBearerAuth, ApiTags } from '@nestjs/swagger';
import * as bcrypt from 'bcrypt';
import { JwtAuthGuard } from 'src/utils/jwt-auth.guard';
import { Response } from 'express';
import { RegisterDto } from './dto/register.dto';

@ApiBearerAuth()
@ApiTags("Auth")
@Controller('auth')
export class AuthController {
  constructor(private readonly authService: AuthService) {}

  @Post('login')
  async login(@Body() loginDto: LoginDto, @Res() res: Response) {
    return res.status(HttpStatus.OK).json(await this.authService.login(loginDto))
  }

  

  @Post('register')
  async createUser(@Body() user: RegisterDto) {
    try {
      console.log(user);
      
      return await this.authService.createUser(user);
    } catch (error) {
      if (error instanceof HttpException) {
        throw error;
      } else {
        return { message: 'No se pudo crear usuario', error: error.message };
      }
    }
  }
}
