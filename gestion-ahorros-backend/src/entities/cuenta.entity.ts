import { Column, Entity, ManyToOne, PrimaryGeneratedColumn } from "typeorm"
import { Usuario } from "./usuario.entity";

@Entity({ name: 'cuenta' })
export class Cuenta {

    @PrimaryGeneratedColumn()
    id: number;

    @Column({
        length: 50
    })
    nombre: string;

    @Column({
        type: 'int',
        nullable: false,
        default: 0
    })
    color: number;

    @Column({
        type: 'int',
        nullable: false,
        default: 0
    })
    icono: number;

    @Column({ default: true })
    activo: boolean;


    @ManyToOne(() => Usuario, user => user.cuentas)
    usuario: Usuario

}