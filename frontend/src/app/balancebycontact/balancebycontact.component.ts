import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-balancebycontact',
  templateUrl: './balancebycontact.component.html',
  styleUrls: ['./balancebycontact.component.css']
})
export class BalancebycontactComponent implements OnInit {

  balancebycontact: any = [];
  loading: boolean = false;
  constructor(public rest: RestapiService, private route: ActivatedRoute, private router: Router) { }

  currentselection: string='';

  ngOnInit() {
  }

  getBalanceByContact(entityname: string) {
    this.loading = true;
    this.balancebycontact = [];

    if (entityname === "*") {
      this.rest.getBalanceByContact().subscribe((data: {}) => {
        console.log(data);
        this.balancebycontact = data;
        this.loading = false;
      });
    }

    else if (entityname && entityname.trim()) {
      this.rest.searchBbcByName(entityname).subscribe((data: {}) => {
        console.log(data);
        this.balancebycontact = data;
        this.loading = false;
      });
    }

    else {
      this.balancebycontact = [];
      this.loading = false;
    }

  }

  onAccountSelection(acct: string){
    this.currentselection = acct
  }

}
