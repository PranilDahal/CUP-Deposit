import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-allaccountbalances',
  templateUrl: './allaccountbalances.component.html',
  styleUrls: ['./allaccountbalances.component.css']
})
export class AllaccountbalancesComponent implements OnInit {

  allaccountbalances:any = [] ;
  constructor(public rest:RestapiService, private route: ActivatedRoute, private router: Router) { }

  // Function that gets called when the componenet is initialized
  ngOnInit() {
    // this.getAllAccountBalances();
  }

  getAllAccountBalances() {
    this.allaccountbalances = [];
    this.rest.getAllAccountBalances().subscribe((data: {}) => {
      console.log(data);
      this.allaccountbalances = data;
    });
  }


}
