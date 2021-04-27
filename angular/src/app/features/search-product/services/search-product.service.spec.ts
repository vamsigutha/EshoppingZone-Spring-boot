/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SearchProductService } from './search-product.service';

describe('Service: SearchProduct', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SearchProductService]
    });
  });

  it('should ...', inject([SearchProductService], (service: SearchProductService) => {
    expect(service).toBeTruthy();
  }));
});
