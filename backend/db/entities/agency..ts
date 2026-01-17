import { Entity, PrimaryGeneratedColumn, Column, OneToMany } from "typeorm";
import { Agent } from "./agent";

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

  /**
   * Agents that belong to this agency
   */
  @OneToMany(() => Agent, (agent) => agent.agency)
  agent!: Agent[];
}
