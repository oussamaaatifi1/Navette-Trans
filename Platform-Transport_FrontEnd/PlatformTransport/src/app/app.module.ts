import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './features/auth/login/login.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { ProfileComponent } from './features/auth/profile/profile.component';
import { ListOffreComponent } from './features/offres-transport/list/list.component';
import { DetailComponent } from './features/offres-transport/detail/detail.component';
import { CreateComponent } from './features/offres-transport/create/create.component';
import { EditComponent } from './features/offres-transport/edit/edit.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HeaderComponent } from './shared/header/header.component';
import { DashboardAdminComponent } from './dashboards/dashboard-admin/dashboard-admin.component';
import { AdminCountComponent } from './dashboards/dashboard-admin/admin-count/admin-count.component';
import { HistoriqueComponent } from './features/reservation/historique/historique.component';
import { CreatereserveComponent } from './features/reservation/createreserve/createreserve.component';
import { EmployeProfileComponent } from './dashboards/dashboard-employe/employe-profile/employe-profile.component';
import { ManagemntOffreComponent } from './dashboards/dashboard-admin/managemnt-offre/managemnt-offre.component';
import { UpdateOffreComponent } from './dashboards/dashboard-admin/update-offre/update-offre.component';
import { OffreDiponibleComponent } from './dashboards/dashboard-employe/offre-diponible/offre-diponible.component';
import { AuthenticationInterceptor } from './core/interceptors/authentication.interceptor';
import { EmployesComponent } from './dashboards/dashboard-admin/employes/employes.component';
import { EmployeursComponent } from './dashboards/dashboard-admin/employeur/employeur.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { DashboardEmployeurComponent } from './dashboards/dashboard-employeur/dashboard-employeur.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { EditEmployesComponent } from './dashboards/dashboard-admin/employes/edit-employes/edit-employes.component';
import { SearchComponent } from './pages/search/search.component';
import { NavbarEmployeComponent } from './shared/navbar-employe/navbar-employe.component';
import { ReserveOffreComponent } from './dashboards/dashboard-employe/reserve-offre/reserve-offre.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { ListReservationEmployeComponent } from './dashboards/dashboard-employeur/list-reservation-employe/list-reservation-employe.component';
import { RegisterPopupComponent } from './dashboards/dashboard-admin/register-employeur/register-employeur.component';
import { AuthService } from './core/services/auth.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    DetailComponent,
    CreateComponent,
    EditComponent,
    ListOffreComponent,
    NavbarComponent,
    SidebarComponent,
    FooterComponent,
    HeaderComponent,
    DashboardAdminComponent,
    AdminCountComponent,
    HistoriqueComponent,
    CreatereserveComponent,
    EmployeProfileComponent,
    ManagemntOffreComponent,
    UpdateOffreComponent,
    OffreDiponibleComponent,
    EmployesComponent,
    EmployeursComponent,
    HomePageComponent,
    DashboardEmployeurComponent,
    EditEmployesComponent,
    SearchComponent,
    NavbarEmployeComponent,
    ReserveOffreComponent,
    ListReservationEmployeComponent,
    RegisterPopupComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatDialogModule,
    MatPaginatorModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true },
    AuthService 
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
