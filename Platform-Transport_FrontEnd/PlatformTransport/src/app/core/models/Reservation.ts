import { Employe } from "./Employe";
import { OffreTransport } from "./OffreTransport";
import { Transaction } from "./Transaction";

export interface Reservation {
    id?: number;
    dateReservation: string;
    employe: Employe;
    offre: OffreTransport;
    transaction?: Transaction;
    offreTransport?: OffreTransport;

}