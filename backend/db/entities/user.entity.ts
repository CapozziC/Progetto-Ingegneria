import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  OneToMany,
} from "typeorm";
import { Appointment } from "./appointment.entity";
import { Offer } from "./offer.entity";
import { ProviderAccount } from "./providerAccount.entity";

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

  @CreateDateColumn({ type: "timestamp with time zone" })
  appointmentDate!: Date;

  @Column()
  password!: string;

  @Column({ default: false })
  isAdmin!: boolean;

  @OneToMany(() => Appointment, (appointment) => appointment.user)
  appointments!: Appointment[];

  @OneToMany(() => Offer, (offer) => offer.user)
  offers!: Offer[];

  @OneToMany(() => ProviderAccount, (providerAccount) => providerAccount.user)
  providerAccounts!: ProviderAccount[];
}
