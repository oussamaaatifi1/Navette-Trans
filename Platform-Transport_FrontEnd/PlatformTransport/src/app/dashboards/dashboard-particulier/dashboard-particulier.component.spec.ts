import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardParticulierComponent } from './dashboard-particulier.component';

describe('DashboardParticulierComponent', () => {
  let component: DashboardParticulierComponent;
  let fixture: ComponentFixture<DashboardParticulierComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DashboardParticulierComponent]
    });
    fixture = TestBed.createComponent(DashboardParticulierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
