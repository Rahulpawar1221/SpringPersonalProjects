import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-to-landlord',
  templateUrl: './register-to-landlord.component.html',
  styleUrls: ['./register-to-landlord.component.css']
})
export class RegisterToLandlordComponent implements OnInit {

  registerForm!: FormGroup;
  message = '';
  error = '';
  isSubmitting = false;

  private token: string | null = null;
  private username: string | null = null;

  constructor(private fb: FormBuilder, private http: HttpClient,private router: Router) {}

  ngOnInit(): void {
    // Setup the form and load auth data
    this.registerForm = this.fb.group({
      tenantFullName: ['', Validators.required],
      tenantEmail: ['', [Validators.required, Validators.email]],
      tenantPhoneNumber: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],
      tenantPermanentAddress: ['', Validators.required],
      aadhaarNumber: ['', [Validators.required, Validators.pattern(/^\d{12}$/)]],
      panCardNumber: ['', [Validators.required, Validators.pattern(/^[A-Z]{5}[0-9]{4}[A-Z]{1}$/)]],
      landlordCode: ['', Validators.required]
    });

    this.token = localStorage.getItem('token');
    this.username = localStorage.getItem('username');
  }

  onSubmit(): void {
    console.log('Form Submitted')
    if (this.registerForm.invalid || this.isSubmitting || !this.token || !this.username) {
      return;
    }

    this.isSubmitting = true;

    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`,
      username: this.username
    });

    this.http.post<any>(
      'http://localhost:8080/api/tenant/register-to-landlord',
      this.registerForm.value,
      { headers }
    ).subscribe({
      next: (res) => {
        this.message = res.message;
        this.error = '';
        this.registerForm.reset();
        this.isSubmitting = false;

        setTimeout(() => {
          this.router.navigate(['/tenant-dashboard']);
        }, 2000);
      },

      
      error: (err) => {
        this.message = '';
        this.error = err.error?.error || 'Something went wrong';
        this.isSubmitting = false;
      }
    });
  }
}
