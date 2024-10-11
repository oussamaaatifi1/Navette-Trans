import { OffreStatus } from "./enum/OffreStatus";
import { TypeOffreTransport } from "./enum/TypeOffreTransport";

export interface OffreTransportDto {
    id: number;
    typeOffreTransport: TypeOffreTransport;
    pointDepart: string;
    destination: string;
    dateOffre: string; // Use string for dates in Angular
    nombrePlaces: number;
    prix: number;
    imgUrl: string;
    status: OffreStatus;
    employeurId: number;
  }
  