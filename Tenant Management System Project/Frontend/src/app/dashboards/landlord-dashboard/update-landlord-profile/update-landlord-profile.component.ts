import { Router } from '@angular/router';
import { Component } from '@angular/core';

@Component({
  selector: 'app-update-landlord-profile',
  templateUrl: './update-landlord-profile.component.html',
  styleUrls: ['./update-landlord-profile.component.css']
})
export class UpdateLandlordProfileComponent {

  constructor(private router: Router) {}

  logout(): void {
    localStorage.clear();
    this.router.navigate(['/']);
  }

  navigateTo(path: string): void {
    this.router.navigate([path]);
  }
}
