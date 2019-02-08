import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllaccountbalancesComponent } from './allaccountbalances.component';

describe('AllaccountbalancesComponent', () => {
  let component: AllaccountbalancesComponent;
  let fixture: ComponentFixture<AllaccountbalancesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllaccountbalancesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllaccountbalancesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
