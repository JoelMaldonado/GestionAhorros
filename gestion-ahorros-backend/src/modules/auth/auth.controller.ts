import { Controller, Post, Body, UseInterceptors } from '@nestjs/common';
import { AuthService } from './auth.service';
import { LoginDto } from './dto/login.dto';
import { ApiBearerAuth, ApiTags } from '@nestjs/swagger';
import { RegisterDto } from './dto/register.dto';
import { TransformResponseInterceptor } from 'src/interceptors/transform_response_interceptor';

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
}
