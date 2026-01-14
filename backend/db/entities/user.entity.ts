import {Entity, PrimaryGeneratedColumn, Column, CreateDateColumn} from 'typeorm';

@Entity("user")
export class User {
    @PrimaryGeneratedColumn()
    id!: number;

    @Column()
    name!: string;

    @Column()
    surname!: string;

    @Column()
    username!: string;

    @Column()
    phoneNumber!: string;

    @CreateDateColumn({ type: 'timestamp with time zone' })
    appointmentDate!: Date;

    @Column()
    password!: string;

    @Column({ default: false })
    isAdmin!: boolean;

    

}
