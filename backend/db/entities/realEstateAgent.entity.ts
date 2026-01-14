import { Entity, PrimaryGeneratedColumn, Column, OneToMany } from "typeorm";
import { Advertisement } from "./advertisement.entity";

@Entity("real_estate_agent")
export class RealEstateAgent {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column()
  name!: string;

  @Column()
  surname!: string;

  @Column()
  password!: string;

  @Column()
  appointmentDate!: Date;

  @Column()
  phoneNumber!: string;

  @OneToMany(() => Advertisement,(advertisement) => advertisement.realEstateAgent)
  advertisements!: Advertisement[];
}
