import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  UpdateDateColumn,
  ManyToOne,
} from "typeorm";

import { User } from "./user.entity";
import { Advertisement } from "./advertisement.entity";
import { Agent } from "./agent.entity";

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
  appointentAt!: Date;

  @CreateDateColumn({ type: "timestamp with time zone" })
  createdAt!: Date;

  @UpdateDateColumn({
    type: "timestamp with time zone",
    default: () => "CURRENT_TIMESTAMP",
  })
  updatedAt!: Date;
  
  @Column({
    type: "enum",
    enum: AppointmentStatus,
    default: AppointmentStatus.REQUESTED,
  })
  status!: AppointmentStatus;

  @ManyToOne(() => User, (user) => user.id, { onDelete: "CASCADE" })
  user!: User;

  @ManyToOne(
    () => Advertisement,
    (advertisement) => advertisement.appointments,
    { onDelete: "CASCADE" }
  )
  advertisement!: Advertisement;

  @ManyToOne(() => Agent, (agent) => agent.appointments, {
    onDelete: "CASCADE",
  })
  agent!: Agent;
}
