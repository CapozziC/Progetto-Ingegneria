import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  ManyToOne,
  UpdateDateColumn,
} from "typeorm";

import { User } from "./localUser";

export enum Provider {
  GOOGLE = "GOOGLE",
  GITHUB = "GITHUB",
  FACEBOOK = "FACEBOOK",
}

@Entity("provider_account")
export class ProviderAccount {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column()
  name!: string;

  @Column()
  surname!: string;

  @Column()
  email!: string;

  @Column({
    type: "enum",
    enum: Provider,
  })
  provider!: Provider;

  @Column()
  providerUserId!: string;

  @CreateDateColumn({ type: "timestamp with time zone" })
  createdAt!: Date;

  @UpdateDateColumn({
    type: "timestamp with time zone",
    default: () => "CURRENT_TIMESTAMP",
  })
  updatedAt!: Date;
}
          