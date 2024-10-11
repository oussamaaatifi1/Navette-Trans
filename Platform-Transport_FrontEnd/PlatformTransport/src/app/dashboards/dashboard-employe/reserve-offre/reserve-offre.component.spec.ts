import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReserveOffreComponent } from './reserve-offre.component';

describe('ReserveOffreComponent', () => {
  let component: ReserveOffreComponent;
  let fixture: ComponentFixture<ReserveOffreComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReserveOffreComponent]
    });
    fixture = TestBed.createComponent(ReserveOffreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
