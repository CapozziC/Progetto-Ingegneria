import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  OneToOne,
  UpdateDateColumn,
} from "typeorm";

export enum SessionType {
  LOCAL = "LOCAL",
  PROVIDER = "PROVIDER",
}

@Entity("session")
export class Session {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column({
    type: "enum",
    enum: SessionType,
  })
  type!: SessionType;

  @Column()
  refreshToken!: string;

  @Column()
  token!: string;

  @Column()
  expiresAt!: Date;

  @CreateDateColumn({ type: "timestamp with time zone" })
  createdAt!: Date;

  @UpdateDateColumn({
    type: "timestamp with time zone",
    default: () => "CURRENT_TIMESTAMP",
  })
  updatedAt!: Date;
}
