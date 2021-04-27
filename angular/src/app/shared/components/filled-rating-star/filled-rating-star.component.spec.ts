/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { FilledRatingStarComponent } from './filled-rating-star.component';

describe('FilledRatingStarComponent', () => {
  let component: FilledRatingStarComponent;
  let fixture: ComponentFixture<FilledRatingStarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilledRatingStarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilledRatingStarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
