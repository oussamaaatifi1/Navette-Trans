import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListOffreComponent } from './features/offres-transport/list/list.component';
import { DashboardAdminComponent } from './dashboards/dashboard-admin/dashboard-admin.component';
import { LoginComponent } from './features/auth/login/login.component';
import { CreateComponent } from './features/offres-transport/create/create.component';
import { AuthGuard } from './core/guards/auth.guard';
import { AdminCountComponent } from './dashboards/dashboard-admin/admin-count/admin-count.component';
import { Role } from './core/models/enum/Role';
import { HistoriqueComponent } from './features/reservation/historique/historique.component';
import { CreatereserveComponent } from './features/reservation/createreserve/createreserve.component';
import { ManagemntOffreComponent } from './dashboards/dashboard-admin/managemnt-offre/managemnt-offre.component';
import { UpdateOffreComponent } from './dashboards/dashboard-admin/update-offre/update-offre.component';
import { OffreDiponibleComponent } from './dashboards/dashboard-employe/offre-diponible/offre-diponible.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { EmployesComponent } from './dashboards/dashboard-admin/employes/employes.component';
import { EmployeursComponent } from './dashboards/dashboard-admin/employeur/employeur.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { DashboardEmployeurComponent } from './dashboards/dashboard-employeur/dashboard-employeur.component';
import { EditEmployesComponent } from './dashboards/dashboard-admin/employes/edit-employes/edit-employes.component';
import { SearchComponent } from './pages/search/search.component';
import { ReserveOffreComponent } from './dashboards/dashboard-employe/reserve-offre/reserve-offre.component';
import { ProfileComponent } from './features/auth/profile/profile.component';
import { ListReservationEmployeComponent } from './dashboards/dashboard-employeur/list-reservation-employe/list-reservation-employe.component';
import { RegisterPopupComponent } from './dashboards/dashboard-admin/register-employeur/register-employeur.component';

const routes: Routes = [
  {
    path: 'offretransports',
    component: ListOffreComponent,
    canActivate: [AuthGuard],
    data: { role: Role.EMPLOYE },
  },
  {
    path: 'dashboard/ADMIN',
    component: DashboardAdminComponent,
    canActivate: [AuthGuard],
    data: { role: Role.ADMIN },
  },
  {
    path: 'dashboard/EMPLOYE',
    component: OffreDiponibleComponent,
    canActivate: [AuthGuard],
    data: { role: Role.EMPLOYE },
  },
  {
    path: 'dashboard/EMPLOYEUR',
    component: DashboardEmployeurComponent,
    canActivate: [AuthGuard],
    data: { role: Role.EMPLOYEUR },
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'register/employeur', component: RegisterComponent },
  {
    path: 'reservation',
    component: ReserveOffreComponent,
    canActivate: [AuthGuard],
    data: { role: Role.EMPLOYE },
  },
  {
    path: 'listOffre',
    component: ListOffreComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.EMPLOYEUR, Role.ADMIN] },
  },
  {
    path: 'CreateOffer',
    component: CreateComponent,
    canActivate: [AuthGuard],
    data: { role: Role.EMPLOYEUR },
  },
  {
    path: 'admin-count',
    component: AdminCountComponent,
    canActivate: [AuthGuard],
    data: { role: Role.ADMIN },
  },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomePageComponent },
  { path: 'historique', component: HistoriqueComponent },
  { path: 'CreateReserve', component: CreatereserveComponent },
  {
    path: 'offrebyadmin',
    component: ManagemntOffreComponent,
    canActivate: [AuthGuard],
    data: { role: Role.ADMIN },
  },
  {
    path: 'offrebyadmin',
    component: ManagemntOffreComponent,
    canActivate: [AuthGuard],
    data: { role: Role.ADMIN },
  },
  {
    path: 'update/:id',
    component: UpdateOffreComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'listdispo',
    component: OffreDiponibleComponent,
    canActivate: [AuthGuard],
    data: { role: Role.EMPLOYE },
  },
  {
    path: 'listemploye',
    component: EmployesComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'listemployeur',
    component: EmployeursComponent,
    canActivate: [AuthGuard],
    data: { role: Role.ADMIN },
  },
  {
    path: 'update-employe/:id',
    component: EditEmployesComponent,
    canActivate: [AuthGuard],
    data: { role: Role.ADMIN },
  },
  { path: 'searchoffre', component: SearchComponent },
  {
    path: 'profiles',
    component: ProfileComponent,
    canActivate: [AuthGuard],
    data: { role: Role.EMPLOYE },
  },
  {
    path: 'listreservation',
    component: ListReservationEmployeComponent,
    canActivate: [AuthGuard],
    data: { role: Role.EMPLOYEUR },
  },
  {
    path: 'allreservation',
    component: ListReservationEmployeComponent,
    canActivate: [AuthGuard],
    data: { role: Role.ADMIN },
  },
  { path: 'register/employeur', component: RegisterPopupComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
