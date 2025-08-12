import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-tenant-profile',
  templateUrl: './update-tenant-profile.component.html',
  styleUrls: ['./update-tenant-profile.component.css']
})
export class UpdateTenantProfileComponent implements OnInit {

  updateForm!: FormGroup;
  message: string = '';
  error: string = '';

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.updateForm = this.fb.group({
      tenantFullName: ['', [Validators.required]],
      tenantEmail: ['', [Validators.required, Validators.email]],
      tenantPhoneNumber: ['', [Validators.required, Validators.pattern(/^[6-9]\d{9}$/)]],
      tenantPermanentAddress: ['', [Validators.required]],
      aadhaarNumber: ['', [Validators.required, Validators.pattern(/^\d{12}$/)]],
      panCardNumber: ['', [Validators.required, Validators.pattern(/^[A-Z]{5}[0-9]{4}[A-Z]{1}$/)]]
    });
  }

  onSubmit(): void {
    if (this.updateForm.invalid) {
      this.error = 'Please fill in all required fields correctly.';
      return;
    }

    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    });

    this.http.put<any>('http://localhost:8080/api/tenant/update/profile', this.updateForm.value, { headers })
      .subscribe({
        next: (response) => {
          this.message = response.message;
          this.error = '';

          // ✅ Redirect to tenant dashboard after short delay
          setTimeout(() => {
            this.router.navigate(['/tenant-dashboard']);
          }, 1500); // 1.5 second delay so user sees success message
        },
        error: (err) => {
          this.error = err.error?.message || 'Update failed. Please try again.';
          this.message = '';
        }
      });
  }
}
