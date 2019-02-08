import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BalancebycontactComponent } from './balancebycontact.component';

describe('BalancebycontactComponent', () => {
  let component: BalancebycontactComponent;
  let fixture: ComponentFixture<BalancebycontactComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BalancebycontactComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BalancebycontactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
