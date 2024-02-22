import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UsuarioModule } from './modules/usuario/usuario.module';
import { TransaccionModule } from './modules/transaccion/transaccion.module';
import { CuentaModule } from './modules/cuenta/cuenta.module';
import { CategoriaModule } from './modules/categoria/categoria.module';
import { AuthModule } from './modules/auth/auth.module';
import typeOrmConfig from './config/typeorm.config';

@Module({
  imports: [
    TypeOrmModule.forRoot(typeOrmConfig),
    UsuarioModule,
    TransaccionModule,
    CuentaModule,
    CategoriaModule,
    AuthModule,
    
  ],
  controllers: [],
  providers: [],
})
export class AppModule {

}
