import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  UpdateDateColumn,
  ManyToOne,
  OneToMany,
  OneToOne,
} from "typeorm";
import { Agent } from "./agent.entity";
import { RealEstate } from "./realEstate.entity";
import { Appointment } from "./appointment.entity";

import { Photo } from "./photo.entity";
import { Offer } from "./offer.entity";

export enum AdvertisementStatus {
  ACTIVE = "ACTIVE",
  INACTIVE = "INACTIVE",
}

export enum TypeAdvertisement {
  SALE = "SALE",
  RENT = "RENT",
}

@Entity("advertisement")
export class Advertisement {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column()
  description!: string;

  @Column("decimal")
  price!: number;

  @CreateDateColumn({ type: "timestamp with time zone" })
  createdAt!: Date;

  @UpdateDateColumn({
    type: "timestamp with time zone",
    default: () => "CURRENT_TIMESTAMP",
  })
  updatedAt!: Date;

  @Column({
    type: "enum",
    enum: AdvertisementStatus,
    default: AdvertisementStatus.ACTIVE,
  })
  status!: AdvertisementStatus;

  @Column({
    type: "enum",
    enum: TypeAdvertisement,
  })
  type!: TypeAdvertisement;

  @ManyToOne(() => Agent, (agent) => agent.advertisements, {
    onDelete: "CASCADE",
  })
  agent!: Agent;

  @OneToMany(() => Offer, (offer) => offer.advertisement)
  offers!: Offer[];

  @OneToMany(() => Appointment, (appointment) => appointment.advertisement)
  appointments!: Appointment[];

  @OneToMany(() => Photo, (photo) => photo.advertisement)
  photos!: Photo[];

  @OneToOne(() => RealEstate)
  realEstate!: RealEstate;
}
