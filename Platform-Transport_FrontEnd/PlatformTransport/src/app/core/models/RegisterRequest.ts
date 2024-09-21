import { Role } from "./enum/Role";

export interface RegisterRequest {
  nom: string;
  prenom : string;
  email: string;
  password: string;
}
