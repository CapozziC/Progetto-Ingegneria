import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  ManyToOne,
  OneToMany,
  OneToOne,
} from "typeorm";
import { RealEstateAgent } from "./realEstateAgent.entity";
import { RealEstate } from "./realEstate.entity";
import { Appointment } from "./appointment.entity";

import { Photo } from "./photo.entity";
import { Offer } from "./offer.entity";

export enum AdvertisementStatus {
  ACTIVE = "ACTIVE",
  INACTIVE = "INACTIVE",
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

  @CreateDateColumn({ type: "timestamp with time zone" })
  UpdateAt!: Date;

  @Column({
    type: "enum",
    enum: AdvertisementStatus,
    default: AdvertisementStatus.ACTIVE,
  })
  status!: AdvertisementStatus;

  @ManyToOne(
    () => RealEstateAgent,
    (realEstateAgent) => realEstateAgent.advertisements,
    { onDelete: "CASCADE" }
  )
  realEstateAgent!: RealEstateAgent;

  @OneToMany(() => Offer, (offer) => offer.advertisement)
  offers!: Offer[];

  @OneToMany(() => Appointment, (appointment) => appointment.advertisement)
  appointments!: Appointment[];

  @OneToMany(() => Photo, (photo) => photo.advertisement)
  photos!: Photo[];

  @OneToOne(() => RealEstate, (realEstate) => realEstate.advertisement, {
    onDelete: "CASCADE",
  })
  realEstate!: RealEstate;
}
