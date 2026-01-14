import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  ManyToOne,
} from "typeorm";

import { User } from "./user.entity";
import { Advertisement } from "./advertisement.entity";
import { RealEstateAgent } from "./realEstateAgent.entity";

export enum AppointmentStatus {
  REQUESTED = "REQUESTED",
  CONFIRMED = "CONFIRMED",
  CANCELLED = "CANCELLED",
  COMPLETED = "COMPLETED",
}

@Entity("appointment")
export class Appointment {
  @PrimaryGeneratedColumn()
  id!: number;

  @CreateDateColumn({ type: "timestamp with time zone" })
  createdAt!: Date;

  @Column({
    type: "enum",
    enum: AppointmentStatus,
    default: AppointmentStatus.REQUESTED,
  })
  status!: AppointmentStatus;

    @ManyToOne(() => User, (user) => user.id, { onDelete: "CASCADE" })
    user!: User;

    @ManyToOne(() => Advertisement, (advertisement) => advertisement.appointments, { onDelete: "CASCADE" })
    advertisement!: Advertisement;

    @ManyToOne(() => RealEstateAgent, (realEstateAgent) => realEstateAgent.appointments, { onDelete: "CASCADE" })
    realEstateAgent!: RealEstateAgent;
    
}
