import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  OneToOne,
} from "typeorm";
import { Advertisement } from "./advertisement.entity";
import { User } from "./user.entity";
import { RealEstateAgent } from "./realEstateAgent.entity";
import { RealEstateAgency } from "./realEstateAgency.entity";

export enum Format {
  JPEG = "JPEG",
  PNG = "PNG",
  HEIC = "HEIC",
}

@Entity("photo")
export class Photo {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column()
  url!: string;

  @Column({ type: "int" })
  position!: number;

  @Column({
    type: "enum",
    enum: Format,
  })
  format!: Format;

  @ManyToOne(() => Advertisement, (advertisement) => advertisement.photos, {
    onDelete: "CASCADE",
  })
  advertisement!: Advertisement;

  @OneToOne(() => RealEstateAgency, (realEstateAgency) => realEstateAgency.id, {
    onDelete: "CASCADE",
  })
  realEstateAgency!: RealEstateAgency;

  @OneToOne(() => RealEstateAgent, (realEstateAgent) => realEstateAgent.id, {
    onDelete: "CASCADE",
  })
  realEstateAgent!: RealEstateAgent;

  @OneToOne(() => User, (user) => user.id, {
    onDelete: "CASCADE",
  })
  user!: User;
}
