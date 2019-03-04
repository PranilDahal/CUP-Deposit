import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-balancebycontact',
  templateUrl: './balancebycontact.component.html',
  styleUrls: ['./balancebycontact.component.css']
})
export class BalancebycontactComponent implements OnInit {

  balancebycontact:any = [] ;
  constructor(public rest:RestapiService, private route: ActivatedRoute, private router: Router) { }

  
  ngOnInit() {

	// this.getBalanceByContact();

  }
  
  getBalanceByContact() {
    this.balancebycontact = [];
    this.rest.getBalanceByContact().subscribe((data: {}) => {
      console.log(data);
      this.balancebycontact = data;
    });
  }

}
