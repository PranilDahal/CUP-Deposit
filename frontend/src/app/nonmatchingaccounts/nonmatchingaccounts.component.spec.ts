import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NonmatchingaccountsComponent } from './nonmatchingaccounts.component';

describe('NonmatchingaccountsComponent', () => {
  let component: NonmatchingaccountsComponent;
  let fixture: ComponentFixture<NonmatchingaccountsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NonmatchingaccountsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NonmatchingaccountsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
