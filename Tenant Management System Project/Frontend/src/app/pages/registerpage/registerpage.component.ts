import { Component } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registerpage',
  templateUrl: './registerpage.component.html',
  styleUrls: ['./registerpage.component.css']
})
export class RegisterpageComponent {
  registerData = {
    username: '',
    password: ''
  };

  showPassword: boolean = false;

  constructor(private authService: AuthService, private router: Router) {}

  onRegister() {
    this.authService.register(this.registerData).subscribe({
      next: (res) => {
        alert(res);
        this.router.navigate(['/login']);
      },
      error: () => {
        alert('Registration failed. Username might be taken.');
      }
    });
  }
}
