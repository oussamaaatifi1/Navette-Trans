import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListReservationEmployeComponent } from './list-reservation-employe.component';

describe('ListReservationEmployeComponent', () => {
  let component: ListReservationEmployeComponent;
  let fixture: ComponentFixture<ListReservationEmployeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListReservationEmployeComponent]
    });
    fixture = TestBed.createComponent(ListReservationEmployeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
