import { Role } from "./enum/Role";

export interface RegisterRequest {
  nom: string;
  email: string;
  password: string;
}
