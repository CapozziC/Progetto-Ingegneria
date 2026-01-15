import { Entity, PrimaryGeneratedColumn, Column } from "typeorm";

export enum EnergyClass {
  A = "A",
  B = "B",
  C = "C",
  D = "D",
  E = "E",
  F = "F",
  G = "G",
}

export enum HousingType {
  APARTMENT = "APARTMENT",
  VILLA = "VILLA",
}

export enum OutdoorSpace {
  NONE = "NONE",
  BALCONY = "BALCONY",
  TERRACE = "TERRACE",
  GARDEN = "GARDEN",
}

@Entity("real_estate")
export class RealEstate {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column({ type: "int" })
  size!: number;

  @Column({ type: "int" })
  rooms!: number;

  @Column()
  floor!: String;

  @Column({ default: false })
  elevator!: Boolean;

  @Column({ default: false })
  airConditioning!: Boolean;

  @Column({ default: false })
  heating!: Boolean;

  @Column({ default: false })
  concierge!: Boolean;

  @Column({ default: false })
  parking!: Boolean;

  @Column({ default: false })
  garage!: Boolean;

  @Column({ default: false })
  furnished!: Boolean;

  @Column({ default: false })
  solarPanels!: Boolean;

  @Column({ type: "enum", enum: EnergyClass })
  energyClass!: EnergyClass;

  @Column({ type: "enum", enum: OutdoorSpace })
  outdoorSpace!: OutdoorSpace;

  @Column({ type: "enum", enum: HousingType })
  housingType!: HousingType;
}
