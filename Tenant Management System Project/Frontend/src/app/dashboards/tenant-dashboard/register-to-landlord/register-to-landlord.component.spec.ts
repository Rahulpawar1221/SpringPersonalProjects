import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterToLandlordComponent } from './register-to-landlord.component';

describe('RegisterToLandlordComponent', () => {
  let component: RegisterToLandlordComponent;
  let fixture: ComponentFixture<RegisterToLandlordComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegisterToLandlordComponent]
    });
    fixture = TestBed.createComponent(RegisterToLandlordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
