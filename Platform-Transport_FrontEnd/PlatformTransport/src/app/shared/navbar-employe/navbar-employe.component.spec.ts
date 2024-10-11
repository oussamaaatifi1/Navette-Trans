import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarEmployeComponent } from './navbar-employe.component';

describe('NavbarEmployeComponent', () => {
  let component: NavbarEmployeComponent;
  let fixture: ComponentFixture<NavbarEmployeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NavbarEmployeComponent]
    });
    fixture = TestBed.createComponent(NavbarEmployeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
