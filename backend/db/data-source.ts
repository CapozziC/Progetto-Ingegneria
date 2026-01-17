import "reflect-metadata";
import { DataSource } from "typeorm";
import { User } from "../db/entities/user.entity";
import { Advertisement } from "./entities/advertisement";
import { Agency } from "./entities/agency.";
import { Appointment } from "./entities/appointment";
import { Offer } from "./entities/offer";
import { Photo } from "./entities/photo";
import { RealEstate } from "./entities/realEstate";
export const AppDataSource = new DataSource({
  type: "postgres",
  host: process.env.DB_HOST,
  port: Number(process.env.DB_PORT),
  username: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_NAME,

  entities: [User, Advertisement, Agency, Appointment, Offer, Photo, RealEstate],
  migrations: ["db/migrations/*.ts"],

  synchronize: false,
  logging: false,
});