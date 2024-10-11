import { Employe } from "./Employe";

export class ReservationDto {
        id!: number;
        dateReservation!: Date;
        employeId!: number;
        offreId!: number;
        montant!: number;
        transactionId!: number | null;
        nombrePlaces!: number;
        pointDepart!: string;
        destination!: string;
}
