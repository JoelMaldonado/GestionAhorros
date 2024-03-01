import { Column, Entity, JoinColumn, ManyToOne, OneToOne, PrimaryGeneratedColumn } from "typeorm"
import { Usuario } from "./usuario.entity";
import { Cuenta } from "./cuenta.entity";
import { Categoria } from "./categoria.entity";

@Entity({ name: 'transaccion' })
export class Transaccion {

    @PrimaryGeneratedColumn()
    id: number;

    @Column({
        type: 'enum',
        enum: ['Gasto', 'Ingreso']
    })
    tipo?: 'Gasto' | 'Ingreso';

    @Column({ 
        type: 'double',
        nullable: false
     })
    monto: number;

    @Column({
        length: 100,
        nullable: true
    })
    detalle?: string;

    @Column({
        length: 50,
        nullable: true
    })
    foto?: string;

    @Column({ default: true })
    activo: boolean;

    @Column({ type: 'timestamp', default: () => 'CURRENT_TIMESTAMP' })
    created_at: Date;

    @Column({ type: 'timestamp', default: () => 'CURRENT_TIMESTAMP', onUpdate: 'CURRENT_TIMESTAMP' })
    update_at: Date;

    @ManyToOne(() => Usuario, user => user.cuentas)
    usuario: Usuario

    @OneToOne(() => Cuenta)
    @JoinColumn()
    cuenta: Cuenta

    @OneToOne(() => Categoria)
    @JoinColumn()
    categoria: Categoria

}