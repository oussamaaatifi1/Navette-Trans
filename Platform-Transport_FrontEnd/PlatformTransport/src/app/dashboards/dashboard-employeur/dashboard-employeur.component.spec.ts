import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardEmployeurComponent } from './dashboard-employeur.component';

describe('DashboardEmployeurComponent', () => {
  let component: DashboardEmployeurComponent;
  let fixture: ComponentFixture<DashboardEmployeurComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DashboardEmployeurComponent]
    });
    fixture = TestBed.createComponent(DashboardEmployeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
