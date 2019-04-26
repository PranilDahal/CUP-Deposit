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
  loading:boolean=false;
  constructor(public rest:RestapiService, private route: ActivatedRoute, private router: Router) { }

  // Function that gets called when the componenet is initialized
  ngOnInit() {
    // this.getAllAccountBalances();
  }

  getAllAccountBalances() {
    this.loading = true;
    this.allaccountbalances = [];
    this.rest.getAllAccountBalances().subscribe((data: {}) => {
      console.log(data);
      this.allaccountbalances = data;
      this.loading = false;
    });
  }

  getAllAccountBalanceByDistrict(entityname: string){
    this.loading = true;
    this.allaccountbalances = [];

    if (entityname === "*") {
      this.rest.getAllAccountBalances().subscribe((data: {}) => {
        console.log(data);
        this.allaccountbalances = data;
        this.loading = false;
      });
    }

    else if (entityname && entityname.trim()) {
      this.rest.searchAllAccountBalancesByDistrict(entityname).subscribe((data: {}) => {
        console.log(data);
        this.allaccountbalances = data;
        this.loading = false;
      });
    }

    else {
      this.allaccountbalances = [];
      this.loading = false;
    }

  }


}
