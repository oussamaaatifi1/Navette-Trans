import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterEmployeurComponent } from './register-employeur.component';

describe('RegisterEmployeurComponent', () => {
  let component: RegisterEmployeurComponent;
  let fixture: ComponentFixture<RegisterEmployeurComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegisterEmployeurComponent]
    });
    fixture = TestBed.createComponent(RegisterEmployeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
