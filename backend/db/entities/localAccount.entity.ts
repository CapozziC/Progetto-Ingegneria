import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  OneToOne,
} from "typeorm";

import { User } from "./user.entity";

@Entity("local_account")
export class LocalAccount {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column()
  email!: string;

  @Column()
  passwordHash!: string;

  @CreateDateColumn({ type: "timestamp with time zone" })
  authDate!: Date;

  @OneToOne(() => User, { nullable: true, onDelete: "CASCADE" })
  user?: User;
}
