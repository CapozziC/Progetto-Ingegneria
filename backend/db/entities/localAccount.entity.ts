import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
} from "typeorm";

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
}
