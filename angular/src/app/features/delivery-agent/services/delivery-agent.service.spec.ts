/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { DeliveryAgentService } from './delivery-agent.service';

describe('Service: DeliveryAgent', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DeliveryAgentService]
    });
  });

  it('should ...', inject([DeliveryAgentService], (service: DeliveryAgentService) => {
    expect(service).toBeTruthy();
  }));
});
