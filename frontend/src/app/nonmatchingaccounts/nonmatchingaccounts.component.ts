import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-nonmatchingaccounts',
  templateUrl: './nonmatchingaccounts.component.html',
  styleUrls: ['./nonmatchingaccounts.component.css']
})
export class NonmatchingaccountsComponent implements OnInit {

  nonmatchingaccounts:any = [] ;
  
  constructor(public rest:RestapiService, private route: ActivatedRoute, private router: Router) { }

  // Function that gets called when the componenet is initialized
  ngOnInit() {
    this.getNonMatchingAccounts();
  }

  getNonMatchingAccounts() {
    this.nonmatchingaccounts = [];
    this.rest.getAllNonmatchingAccounts().subscribe((data: {}) => {
      console.log(data);
      this.nonmatchingaccounts = data;
    });
  }

}
