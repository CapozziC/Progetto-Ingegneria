import { Entity, PrimaryGeneratedColumn, Column, OneToMany } from "typeorm";
import { Agent } from "./agent.entity";

@Entity("agency")
export class Agency {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column()
  name!: string;

  @Column()
  phoneNumber!: string;

  @Column()
  email?: string;

  @OneToMany(() => Agent, (agent) => agent.agency)
  agent!: Agent[];
}
