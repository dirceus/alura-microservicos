import { TestBed } from '@angular/core/testing';

import { EmailNaoExisteValidatorService } from './email-nao-existe.validator.service';

describe('EmailNaoExisteValidatorService', () => {
  let service: EmailNaoExisteValidatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmailNaoExisteValidatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
