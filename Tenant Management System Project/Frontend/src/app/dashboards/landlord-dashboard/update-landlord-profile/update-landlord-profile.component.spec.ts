import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateLandlordProfileComponent } from './update-landlord-profile.component';

describe('UpdateLandlordProfileComponent', () => {
  let component: UpdateLandlordProfileComponent;
  let fixture: ComponentFixture<UpdateLandlordProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateLandlordProfileComponent]
    });
    fixture = TestBed.createComponent(UpdateLandlordProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
