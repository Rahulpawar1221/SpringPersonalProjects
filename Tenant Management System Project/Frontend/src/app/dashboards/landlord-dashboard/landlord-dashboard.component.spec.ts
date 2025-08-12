import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LandlordDashboardComponent } from './landlord-dashboard.component';

describe('LandlordDashboardComponent', () => {
  let component: LandlordDashboardComponent;
  let fixture: ComponentFixture<LandlordDashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LandlordDashboardComponent]
    });
    fixture = TestBed.createComponent(LandlordDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
