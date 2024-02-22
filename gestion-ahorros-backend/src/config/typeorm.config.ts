import { TypeOrmModuleOptions } from "@nestjs/typeorm";

const typeOrmConfig: TypeOrmModuleOptions = {
    type: 'mysql',
    port: 3306,
    username: 'atmosfera',
    password: 'Adrilito02*',
    host: '161.132.47.45',
    database: 'gestion_ahorros',
    entities: [__dirname + '/../**/*.entity{.ts,.js}'],
    synchronize: true,
};

export default typeOrmConfig;