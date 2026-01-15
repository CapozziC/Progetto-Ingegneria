import {
  Entity,
  PrimaryGeneratedColumn,
  CreateDateColumn,
  Column,
  OneToMany,
  ManyToOne,
} from "typeorm";
import { Advertisement } from "./advertisement.entity";
import { Offer } from "./offer.entity";
import { Appointment } from "./appointment.entity";
import { Agency } from "./agency.entity";

@Entity("agent")
export class Agent {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column()
  name!: string;

  @Column()
  surname!: string;

  @Column()
  username!: string;

  @Column()
  password!: string;

  @CreateDateColumn({ type: "timestamp with time zone" })
  createdAt!: Date;

  @Column()
  phoneNumber!: string;

  @Column({ default: false })
  isAdmin!: boolean;

  @CreateDateColumn({ type: "timestamp with time zone" })
  appointmentDate!: Date;

  @OneToMany(() => Advertisement, (advertisement) => advertisement.agent)
  advertisements!: Advertisement[];

  @OneToMany(() => Offer, (offer) => offer.agent)
  offers!: Offer[];

  @OneToMany(() => Appointment, (appointment) => appointment.agent)
  appointments!: Appointment[];

  @ManyToOne(() => Agency, (realEstateAgency) => realEstateAgency.agent, {
    onDelete: "CASCADE",
  })
  agency!: Agency;

  @ManyToOne(() => Agent, (agent) => agent.agents)
  administrator!: Agent;

  @OneToMany(() => Agent, (agent) => agent.administrator)
  agents!: Agent[];
}
