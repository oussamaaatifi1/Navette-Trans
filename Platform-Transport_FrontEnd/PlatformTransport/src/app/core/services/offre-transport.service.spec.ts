import { TestBed } from '@angular/core/testing';

import { OffreTransportService } from './offre-transport.service';

describe('OffreTransportService', () => {
  let service: OffreTransportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OffreTransportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
