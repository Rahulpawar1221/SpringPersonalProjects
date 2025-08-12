import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewTenantProfileComponent } from './view-tenant-profile.component';

describe('ViewTenantProfileComponent', () => {
  let component: ViewTenantProfileComponent;
  let fixture: ComponentFixture<ViewTenantProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewTenantProfileComponent]
    });
    fixture = TestBed.createComponent(ViewTenantProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
