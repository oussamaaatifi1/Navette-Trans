export interface Transaction {
    id: number;           // ID de la transaction
    date: string;        // Date de la transaction (format string pour le front-end)
    employeId: number;   // ID de l'employé associé
    reservationId?: number; // ID de la réservation associée (optionnel)
}
