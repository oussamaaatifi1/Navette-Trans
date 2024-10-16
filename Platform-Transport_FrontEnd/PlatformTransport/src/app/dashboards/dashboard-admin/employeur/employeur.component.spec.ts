import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeursComponent } from './employeur.component';

describe('EmployeurComponent', () => {
  let component: EmployeursComponent;
  let fixture: ComponentFixture<EmployeursComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EmployeursComponent]
    });
    fixture = TestBed.createComponent(EmployeursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});