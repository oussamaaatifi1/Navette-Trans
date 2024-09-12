import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeurComponent } from './employeur.component';

describe('EmployeurComponent', () => {
  let component: EmployeurComponent;
  let fixture: ComponentFixture<EmployeurComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EmployeurComponent]
    });
    fixture = TestBed.createComponent(EmployeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
