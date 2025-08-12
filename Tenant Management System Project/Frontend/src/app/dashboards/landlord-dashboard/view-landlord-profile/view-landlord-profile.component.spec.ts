import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewLandlordProfileComponent } from './view-landlord-profile.component';

describe('ViewLandlordProfileComponent', () => {
  let component: ViewLandlordProfileComponent;
  let fixture: ComponentFixture<ViewLandlordProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewLandlordProfileComponent]
    });
    fixture = TestBed.createComponent(ViewLandlordProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
