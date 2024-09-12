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
import { DashboardEmployeComponent } from './dashboards/dashboard-employe/dashboard-employe.component';
import { DashboardEmployeurComponent } from './dashboards/dashboard-employeur/dashboard-employeur.component';
import { DashboardParticulierComponent } from './dashboards/dashboard-particulier/dashboard-particulier.component';
import { AdminCountComponent } from './dashboards/dashboard-admin/admin-count/admin-count.component';
import { HistoriqueComponent } from './features/reservation/historique/historique.component';
import { EmployeComponent } from './features/employes/employe/employe.component';
import { CreatereserveComponent } from './features/reservation/createreserve/createreserve.component';
import { EmployeProfileComponent } from './dashboards/dashboard-employe/employe-profile/employe-profile.component';
import { ManagemntOffreComponent } from './dashboards/dashboard-admin/managemnt-offre/managemnt-offre.component';
import { UpdateOffreComponent } from './dashboards/dashboard-admin/update-offre/update-offre.component';
import { OffreDiponibleComponent } from './dashboards/dashboard-employe/offre-diponible/offre-diponible.component';
import { AuthenticationInterceptor } from './core/interceptors/authentication.interceptor';
import { EmployesComponent } from './dashboards/dashboard-admin/employes/employes.component';
import { EmployeursComponent } from './dashboards/dashboard-admin/employeur/employeur.component';
import { HomePageComponent } from './home-page/home-page.component';


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
    DashboardEmployeComponent,
    DashboardEmployeurComponent,
    DashboardParticulierComponent,
    AdminCountComponent,
    HistoriqueComponent,
    EmployeComponent,
    CreatereserveComponent,
    EmployeProfileComponent,
    ManagemntOffreComponent,
    UpdateOffreComponent,
    OffreDiponibleComponent,
    EmployesComponent,
    EmployeursComponent,
    HomePageComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, 
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
