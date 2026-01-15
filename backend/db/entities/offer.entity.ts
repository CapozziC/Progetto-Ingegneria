import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  ManyToOne,
} from "typeorm";

import { Advertisement } from "./advertisement.entity";
import { User } from "./user.entity";
import { Agent } from "./agent.entity";

export enum OfferStatus {
  PENDING = "PENDING",
  ACCEPTED = "ACCEPTED",
  REJECTED = "REJECTED",
}

@Entity("offer")
export class Offer {
  @PrimaryGeneratedColumn()
  id!: number;

  @CreateDateColumn({ type: "timestamp with time zone" })
  createdAt!: Date;

  @Column({ type: "decimal" })
  price!: number;

  @Column({
    type: "enum",
    enum: OfferStatus,
    default: OfferStatus.PENDING,
  })
  status!: OfferStatus;

  @ManyToOne(() => Advertisement, (advertisement) => advertisement.offers, {
    onDelete: "CASCADE",
  })
  advertisement!: Advertisement;

  @ManyToOne(() => User, (user) => user.id, { onDelete: "CASCADE" })
  user!: User;

  @ManyToOne(() => Agent, (agent) => agent.offers, { onDelete: "CASCADE" })
  agent!: Agent;
}
