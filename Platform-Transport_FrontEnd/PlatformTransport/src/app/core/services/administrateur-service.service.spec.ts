import { TestBed } from '@angular/core/testing';

import { AdministrateurServiceService } from './administrateur-service.service';

describe('AdministrateurServiceService', () => {
  let service: AdministrateurServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdministrateurServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
