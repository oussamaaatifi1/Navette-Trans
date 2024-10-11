export interface Employe {
    id: number;
    nom: string;
    prenom: string;
    email: string;
    password: string;
    role: string;
    imgUrl: string;
    phone?: string;
    address?: string;
    dateOfBirth?: string;
}
