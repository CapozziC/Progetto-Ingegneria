import {Entity, PrimaryGeneratedColumn, Column} from 'typeorm';

@Entity("real_estate_agency")
export class RealEstateAgency {
    @PrimaryGeneratedColumn()
    id!: number;

    @Column()
    name!: string;

    @Column()
    address!: string;

    @Column()
    phoneNumber!: string;

    @Column()
    email?: string;
}