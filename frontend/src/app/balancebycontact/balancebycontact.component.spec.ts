import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BalancebyplanComponent } from './balancebyplan.component';

describe('BalancebyplanComponent', () => {
  let component: BalancebyplanComponent;
  let fixture: ComponentFixture<BalancebyplanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BalancebyplanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BalancebyplanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
