import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  OneToOne,
} from "typeorm";
import { Advertisement } from "./advertisement.entity";
import { User } from "./user.entity";
import { Agent } from "./agent.entity";
import { Agency } from "./agency.entity";

export enum Format {
  JPEG = "JPEG",
  PNG = "PNG",
  HEIC = "HEIC",
}

@Entity("photo")
export class Photo {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column({
    type: "enum",
    enum: Format,
  })
  format!: Format;

  @Column()
  url!: string;

  @Column({
    type: "int",
    default: 0,
  })
  poition!: number;

  @ManyToOne(() => Advertisement, (advertisement) => advertisement.photos, {
    onDelete: "CASCADE",
  })
  advertisement!: Advertisement;

  @OneToOne(() => Agency, {
    onDelete: "CASCADE",
  })
  agency!: Agency;

  @OneToOne(() => User, (user) => user.id, {
    onDelete: "CASCADE",
  })
  user!: User;
}
