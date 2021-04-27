/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { ChevronDownComponent } from './chevron-down.component';

describe('ChevronDownComponent', () => {
  let component: ChevronDownComponent;
  let fixture: ComponentFixture<ChevronDownComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChevronDownComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChevronDownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
