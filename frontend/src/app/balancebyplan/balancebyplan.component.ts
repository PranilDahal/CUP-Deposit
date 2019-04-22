import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-balancebyplan',
  templateUrl: './balancebyplan.component.html',
  styleUrls: ['./balancebyplan.component.css']
})
export class BalancebyplanComponent implements OnInit {

  balancebyplan:any = [] ;
  constructor(public rest:RestapiService, private route: ActivatedRoute, private router: Router) { }
  
  planselection: string='';
  acctselection: string='';

  ngOnInit() {
  }

  getBalanceByPlan(entityname: string) {
    this.balancebyplan = [];

    if (entityname === "*") {
      this.rest.getBalanceByPlan().subscribe((data: {}) => {
        console.log(data);
        this.balancebyplan = data;
      });
    }

    else if (entityname && entityname.trim()) {
      this.rest.searchBbpByNumber(entityname).subscribe((data: {}) => {
        console.log(data);
        this.balancebyplan = data;
      });
    }

    else {
      this.balancebyplan = [];
    }

  }

  onAccountSelection(acct: string){
    this.acctselection = acct
  }

  onPlanSelection(plan: string){
    this.planselection = plan
  }

}
