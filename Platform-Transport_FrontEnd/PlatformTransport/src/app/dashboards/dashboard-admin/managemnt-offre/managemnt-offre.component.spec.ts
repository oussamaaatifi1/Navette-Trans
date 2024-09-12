import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagemntOffreComponent } from './managemnt-offre.component';

describe('ManagemntOffreComponent', () => {
  let component: ManagemntOffreComponent;
  let fixture: ComponentFixture<ManagemntOffreComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManagemntOffreComponent]
    });
    fixture = TestBed.createComponent(ManagemntOffreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
