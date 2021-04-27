/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SingleProductService } from './single-product.service';

describe('Service: SingleProduct', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SingleProductService]
    });
  });

  it('should ...', inject([SingleProductService], (service: SingleProductService) => {
    expect(service).toBeTruthy();
  }));
});
