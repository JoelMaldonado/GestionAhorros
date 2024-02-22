import { Column, Entity, OneToMany, PrimaryGeneratedColumn } from "typeorm"
import { Cuenta } from "./cuenta.entity";
import { Categoria } from "./categoria.entity";
import { Transaccion } from "./transaccion.entity";

@Entity({ name: 'usuario' })
export class Usuario {

    @PrimaryGeneratedColumn()
    id: number;

    @Column({
        length: 50,
        nullable: true
    })
    nombre?: string;

    @Column({
        length: 50,
        nullable: true
    })
    apellido?: string;

    @Column({ unique: true })
    correo: string;

    @Column()
    clave: string;

    @Column({ default: true })
    activo: boolean;

    @Column({ type: 'timestamp', default: () => 'CURRENT_TIMESTAMP' })
    created_at: Date;

    @Column({ type: 'timestamp', default: () => 'CURRENT_TIMESTAMP', onUpdate: 'CURRENT_TIMESTAMP' })
    update_at: Date;

    @OneToMany(()=> Cuenta, cuenta => cuenta.usuario)
    cuentas: Cuenta[]

    @OneToMany(()=> Categoria, cat => cat.usuario)
    categorias: Categoria[]

    @OneToMany(()=> Transaccion, trans => trans.usuario)
    transacciones: Transaccion[]
}