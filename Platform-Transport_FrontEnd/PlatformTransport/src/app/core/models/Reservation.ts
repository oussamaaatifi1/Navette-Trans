import { Employe } from "./Employe";
import { OffreTransport } from "./OffreTransport";
import { Transaction } from "./Transaction";

export interface Reservation {
    id?: number;
    dateReservation: string;
    employe?: Employe; // Kept | undefined to make it optional
    offre: OffreTransport;
    transaction?: Transaction;
    offreTransport?: OffreTransport;
}
