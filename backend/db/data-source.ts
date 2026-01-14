import "reflect-metadata";
import { DataSource } from "typeorm";
import { User } from "../db/entities/user.entity";
import dotenv from "dotenv";
export const AppDataSource = new DataSource({
  type: "postgres",
  host: process.env.DB_HOST,
  port: Number(process.env.DB_PORT ?? 5432),
  username: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_NAME,

  entities: [User],
  migrations: ["db/migrations/*.ts"],

  synchronize: false, // IMPORTANTISSIMO: usa migrations in prod
  logging: false,
});