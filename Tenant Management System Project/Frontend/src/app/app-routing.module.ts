import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingpageComponent } from './pages/landingpage/landingpage.component';
import { LoginpageComponent } from './pages/loginpage/loginpage.component';
import { RegisterpageComponent } from './pages/registerpage/registerpage.component';
import { TenantDashboardComponent } from './dashboards/tenant-dashboard/tenant-dashboard.component';
import { LandlordDashboardComponent } from './dashboards/landlord-dashboard/landlord-dashboard.component';
import { AdminDashboardComponent } from './dashboards/admin-dashboard/admin-dashboard.component';
import { RegisterToLandlordComponent } from './dashboards/tenant-dashboard/register-to-landlord/register-to-landlord.component';
import { UpdateTenantProfileComponent } from './dashboards/tenant-dashboard/update-tenant-profile/update-tenant-profile.component';
import { ViewTenantProfileComponent } from './dashboards/tenant-dashboard/view-tenant-profile/view-tenant-profile.component';
import { UpdateLandlordProfileComponent } from './dashboards/landlord-dashboard/update-landlord-profile/update-landlord-profile.component';
import { ViewLandlordProfileComponent } from './dashboards/landlord-dashboard/view-landlord-profile/view-landlord-profile.component';
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
  {path: '', component:LandingpageComponent},
  {path: 'login',component:LoginpageComponent},
  {path: 'register', component:RegisterpageComponent},

  {path: 'tenant-dashboard', component:TenantDashboardComponent, canActivate: [AuthGuard],data: { role: 'TENANT' }},
  {path: 'tenant-dashboard/register-to-landlord', component:RegisterToLandlordComponent},
  {path: 'tenant-dashboard/update-tenant-profile', component:UpdateTenantProfileComponent},
  {path: 'tenant-dashboard/view-tenant-profile', component: ViewTenantProfileComponent},

  {path: 'landlord-dashboard', component:LandlordDashboardComponent,canActivate:[AuthGuard], data:{role:'LANDLORD'}},
  {path: 'landlord-dashboard/update-landlord-profile', component:UpdateLandlordProfileComponent},
  {path: 'landlord-dashboard/view-landlord-profile', component:ViewLandlordProfileComponent},
  {path: 'admin-dashboard', component:AdminDashboardComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
