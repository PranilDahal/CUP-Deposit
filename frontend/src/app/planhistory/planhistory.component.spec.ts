import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanhistoryComponent } from './planhistory.component';

describe('PlanhistoryComponent', () => {
  let component: PlanhistoryComponent;
  let fixture: ComponentFixture<PlanhistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlanhistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlanhistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
