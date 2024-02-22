import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { ValidationPipe } from '@nestjs/common';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  const config = new DocumentBuilder()
  .addBearerAuth()
    .setTitle('Gestion Ahorros')
    .setDescription('Documentación de APIs')
    .setVersion('1.0')
    .addTag('Auth')
    .addTag('Usuario')
    .addTag('Cuenta')
    .addTag('Categoria')
    .addTag('Transacción')
    .build();
  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('docs', app, document);

  app.useGlobalPipes(new ValidationPipe());

  await app.listen(3000);
}
bootstrap();
