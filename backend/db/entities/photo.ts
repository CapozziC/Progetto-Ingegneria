import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  OneToOne,
} from "typeorm";
import { Advertisement } from "./advertisement";
import { User } from "./user.entity";
import { Agent } from "./agent";
import { Agency } from "./agency.";

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

  /**
   * Advertisement this photo refers to
   * If the advertisement is deleted, the photo is deleted as well.
   */
  @ManyToOne(() => Advertisement, (advertisement) => advertisement.photos, {
    onDelete: "CASCADE",
  })
  advertisement!: Advertisement;

  /**
   * Agency this photo refers to
   * If the agency is deleted, the photo is deleted as well.
   */
  @OneToOne(() => Agency, {
    onDelete: "CASCADE",
  })
  agency!: Agency;

  /**
   * User this photo refers to
   * If the user is deleted, the photo is deleted as well.
   */
  @OneToOne(() => User, (user) => user.id, {
    onDelete: "CASCADE",
  })
  user!: User;
}
