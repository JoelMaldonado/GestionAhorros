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
  constructor(private readonly authService: AuthService) { }

  @Post('login')
  async login(@Body() loginDto: LoginDto, @Res() res: Response) {
    try {
      const token = await this.authService.login(loginDto)
      return res.json({
        success: true,
        data: token
      })
    } catch (error) {
      return res.json({
        sucscess: false,
        message: error.message
      });
    }
  }



  @Post('register')
  async createUser(@Body() user: RegisterDto, @Res() res: Response) {
    try {
      const data =  await this.authService.createUser(user);
      return res.json({
        success: true,
        data
      })
    } catch (error) {
      return res.json({
        success: false,
        message: error.message
      })
    }
  }
}
