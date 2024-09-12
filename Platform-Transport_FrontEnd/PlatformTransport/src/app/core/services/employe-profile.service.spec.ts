import { TestBed } from '@angular/core/testing';

import { EmployeProfileService } from './employe-profile.service';

describe('EmployeProfileService', () => {
  let service: EmployeProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmployeProfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
