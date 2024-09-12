import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OffreDiponibleComponent } from './offre-diponible.component';

describe('OffreDiponibleComponent', () => {
  let component: OffreDiponibleComponent;
  let fixture: ComponentFixture<OffreDiponibleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OffreDiponibleComponent]
    });
    fixture = TestBed.createComponent(OffreDiponibleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
