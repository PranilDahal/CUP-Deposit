import { Component, OnInit } from '@angular/core';

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
    this.rest.getBalancesByPlan().subscribe((data: {}) => {
      console.log(data);
      this.balancebyplan = data;
    });
  }

}
