import { TypeOffreTransport } from './enum/TypeOffreTransport';
import { OffreStatus } from './enum/OffreStatus';
import { RejectionReason } from './enum/RejectionReason';

export interface OffreTransport {
  id?: number;
  typeOffreTransport: TypeOffreTransport;
  pointDepart: string;
  destination: string;
  dateOffre: string;
  nombrePlaces: number;
  prix: number;
  imgUrl: string;
  status?: OffreStatus;
  rejectionReason?: RejectionReason;
}
