import { Controller, Post, Body, UseInterceptors, Get, Req, UseGuards } from '@nestjs/common';
import { AuthService } from './auth.service';
import { LoginDto } from './dto/login.dto';
import { ApiBearerAuth, ApiTags } from '@nestjs/swagger';
import { RegisterDto } from './dto/register.dto';
import { TransformResponseInterceptor } from 'src/interceptors/transform_response_interceptor';
import { JwtAuthGuard } from 'src/utils/jwt-auth.guard';
import { Request } from 'express';

@ApiBearerAuth()
@ApiTags("Auth")
@Controller('auth')
export class AuthController {
  constructor(private readonly authService: AuthService) { }

  @UseInterceptors(TransformResponseInterceptor)
  @Post('login')
  async login(@Body() loginDto: LoginDto) {
    return await this.authService.login(loginDto)
  }

  @UseInterceptors(TransformResponseInterceptor)
  @Post('register')
  async createUser(@Body() user: RegisterDto) {
    return await this.authService.createUser(user);
  }

  @UseInterceptors(TransformResponseInterceptor)
  @UseGuards(JwtAuthGuard)
  @Get('token')
  async token(@Req() req:Request) {
    return this.authService.decodeToken(req.headers.authorization)
  }
}
