import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-planhistory',
  templateUrl: './planhistory.component.html',
  styleUrls: ['./planhistory.component.css']
})
export class PlanhistoryComponent implements OnInit, OnChanges {

  @Input() selectedbyuser: string;
  planhistory: any = [];
  constructor(public rest: RestapiService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.getTransHistoryByPlanNumber(this.selectedbyuser);
  }

  // when the uses selects a new plan, detect the changes and grab the curret value, and update the componenet with new transaction history
  ngOnChanges(changes: SimpleChanges) {
    console.log(changes)
    this.getTransHistoryByPlanNumber(changes['selectedbyuser'].currentValue);
  }

  getTransHistoryByPlanNumber(plannumber: string) {
    this.planhistory = [];

    this.rest.getPlanTransHistory(plannumber).subscribe((data: {}) => {
      this.planhistory = data;
    });
  }

}
