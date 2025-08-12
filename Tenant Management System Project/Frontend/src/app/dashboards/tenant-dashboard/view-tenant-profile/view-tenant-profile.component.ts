import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-view-tenant-profile',
  templateUrl: './view-tenant-profile.component.html',
  styleUrls: ['./view-tenant-profile.component.css']
})
export class ViewTenantProfileComponent implements OnInit{

  tenantProfile: any = null;
  errorMessage: string = '';
  loading: boolean = true;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    this.http.get<any>('http://localhost:8080/api/tenant/view/profile', { headers })
      .subscribe({
        next: (res) => {
          this.tenantProfile = res;
          this.loading = false;
        },
        error: (err) => {
          this.errorMessage = err.error?.error || 'Failed to load tenant profile.';
          this.loading = false;
        }
      });
  }
}
