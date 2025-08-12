import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent {
  loginData = {
    username: '',
    password: ''
  };

  showPassword: boolean = false;

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    this.authService.login(this.loginData).subscribe({
      next: (res) => {
        localStorage.setItem('token', res.token);
        localStorage.setItem('role', res.role);
        localStorage.setItem('username', this.loginData.username);

        const userRole = res.role?.replace('ROLE_', '').toUpperCase();

        if (userRole === 'ADMIN') {
          this.router.navigate(['/admin-dashboard']);
        } else if (userRole === 'LANDLORD') {
          this.router.navigate(['/landlord-dashboard']);
        } else if (userRole === 'TENANT') {
          this.router.navigate(['/tenant-dashboard']);
        } else {
          alert('Unknown role detected. Redirecting to landing page.');
          this.router.navigate(['/']);
        }
      },
      error: () => {
        alert('Login failed. Please check your credentials.');
      }
    });
  }
}
