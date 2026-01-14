import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  ManyToOne,
} from "typeorm";

import { User } from "./user.entity";

export enum ProviderName {
  GOOGLE = "GOOGLE",
  GITHUBE = "GITHUB",
  FACEBOOK = "  FACEBOOK",
}

@Entity("provider_account")
export class ProviderAccount {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column()
  providerName!: string;

  @Column()
  token!: string;

  @CreateDateColumn({ type: "timestamp with time zone" })
  authDate!: Date;

  @ManyToOne(() => User, (user) => user.id, { onDelete: "CASCADE" })
  user!: User;
}
          