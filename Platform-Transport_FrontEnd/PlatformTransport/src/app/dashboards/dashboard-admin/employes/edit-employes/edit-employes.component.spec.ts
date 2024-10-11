import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditEmployesComponent } from './edit-employes.component';

describe('EditEmployesComponent', () => {
  let component: EditEmployesComponent;
  let fixture: ComponentFixture<EditEmployesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditEmployesComponent]
    });
    fixture = TestBed.createComponent(EditEmployesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
