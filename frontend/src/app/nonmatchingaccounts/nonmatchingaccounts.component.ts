import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpService } from '../service/http.service';


@Component({
  selector: 'app-nonmatchingaccounts',
  templateUrl: './nonmatchingaccounts.component.html',
  styleUrls: ['./nonmatchingaccounts.component.css']
})
export class NonmatchingaccountsComponent implements OnInit {

  nonmatchingaccounts:any = [] ;
  loading:boolean = false;

  constructor(public rest:RestapiService, private route: ActivatedRoute, private router: Router, public http: HttpService) { }

  // Function that gets called when the componenet is initialized
  ngOnInit() {
    this.getNonMatchingAccounts();
  }

  getNonMatchingAccounts() {
    this.loading = true;
    this.nonmatchingaccounts = [];
    this.rest.getAllNonmatchingAccounts().subscribe((data: {}) => {
      console.log(data);
      this.nonmatchingaccounts = data;
      this.loading=false;
    });
  }

  onClick(account, email){
    const recipient = {
      account: account,
      email: email
    }
    this.http.sendEmail("http://localhost:8080/sendEmail", recipient).subscribe(
      data => {
        let res:any = data;
      }
    )
  }

}
