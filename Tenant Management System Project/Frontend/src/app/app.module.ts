import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from 'src/app/interceptors/auth.interceptor';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginpageComponent } from './pages/loginpage/loginpage.component';
import { LandingpageComponent } from './pages/landingpage/landingpage.component';
import { RegisterpageComponent } from './pages/registerpage/registerpage.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TenantDashboardComponent } from './dashboards/tenant-dashboard/tenant-dashboard.component';
import { LandlordDashboardComponent } from './dashboards/landlord-dashboard/landlord-dashboard.component';
import { AdminDashboardComponent } from './dashboards/admin-dashboard/admin-dashboard.component';
import { RegisterToLandlordComponent } from './dashboards/tenant-dashboard/register-to-landlord/register-to-landlord.component';
import { UpdateTenantProfileComponent } from './dashboards/tenant-dashboard/update-tenant-profile/update-tenant-profile.component';
import { ViewTenantProfileComponent } from './dashboards/tenant-dashboard/view-tenant-profile/view-tenant-profile.component';
import { TenantSidebarComponent } from './dashboards/tenant-dashboard/tenant-sidebar/tenant-sidebar.component';
import { ViewLandlordProfileComponent } from './dashboards/landlord-dashboard/view-landlord-profile/view-landlord-profile.component';
import { UpdateLandlordProfileComponent } from './dashboards/landlord-dashboard/update-landlord-profile/update-landlord-profile.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginpageComponent,
    LandingpageComponent,
    RegisterpageComponent,
    TenantDashboardComponent,
    LandlordDashboardComponent,
    AdminDashboardComponent,
    RegisterToLandlordComponent,
    UpdateTenantProfileComponent,
    ViewTenantProfileComponent,
    TenantSidebarComponent,
    ViewLandlordProfileComponent,
    UpdateLandlordProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,   // if using reactive forms
    HttpClientModule
  ],
  providers: [
    {
  provide: HTTP_INTERCEPTORS,
  useClass: AuthInterceptor,
  multi: true
  }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
