export interface TransactionDto {
    id: number;
    montant: number;
    date: string; // Use string for dates in Angular
    employeId: number;
    reservationId: number;
  }
  