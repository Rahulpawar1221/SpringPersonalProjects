import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateTenantProfileComponent } from './update-tenant-profile.component';

describe('UpdateTenantProfileComponent', () => {
  let component: UpdateTenantProfileComponent;
  let fixture: ComponentFixture<UpdateTenantProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateTenantProfileComponent]
    });
    fixture = TestBed.createComponent(UpdateTenantProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
