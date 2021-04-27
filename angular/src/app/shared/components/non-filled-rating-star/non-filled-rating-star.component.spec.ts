/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { NonFilledRatingStarComponent } from './non-filled-rating-star.component';

describe('NonFilledRatingStarComponent', () => {
  let component: NonFilledRatingStarComponent;
  let fixture: ComponentFixture<NonFilledRatingStarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NonFilledRatingStarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NonFilledRatingStarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
