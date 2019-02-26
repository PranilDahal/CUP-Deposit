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

  
  ngOnInit() {
	this.getBalanceByPlan();
  }
  
  getBalanceByPlan() {
    this.balancebyplan = [];
    this.rest.getBalanceByPlan().subscribe((data: {}) => {
      console.log(data);
      this.balancebyplan = data;
    });
  }

}
