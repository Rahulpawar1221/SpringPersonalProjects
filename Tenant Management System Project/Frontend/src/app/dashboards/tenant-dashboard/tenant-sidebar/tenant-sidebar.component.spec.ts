import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TenantSidebarComponent } from './tenant-sidebar.component';

describe('TenantSidebarComponent', () => {
  let component: TenantSidebarComponent;
  let fixture: ComponentFixture<TenantSidebarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TenantSidebarComponent]
    });
    fixture = TestBed.createComponent(TenantSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
