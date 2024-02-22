import { Column, Entity, ManyToOne, PrimaryGeneratedColumn } from "typeorm"
import { Usuario } from "./usuario.entity";

@Entity({ name: 'categoria' })
export class Categoria {

    @PrimaryGeneratedColumn()
    id: number;

    @Column({
        length: 50
    })
    nombre?: string;

    @Column({
        type: 'enum',
        enum: ['Gasto', 'Ingreso']
    })
    tipo?: 'Gasto' | 'Ingreso';

    @Column({ default: true })
    activo: boolean;


    @ManyToOne(() => Usuario, user => user.categorias)
    usuario: Usuario

}