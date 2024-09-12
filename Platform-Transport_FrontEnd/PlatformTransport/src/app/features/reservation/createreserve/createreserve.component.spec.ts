import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatereserveComponent } from './createreserve.component';

describe('CreatereserveComponent', () => {
  let component: CreatereserveComponent;
  let fixture: ComponentFixture<CreatereserveComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreatereserveComponent]
    });
    fixture = TestBed.createComponent(CreatereserveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
