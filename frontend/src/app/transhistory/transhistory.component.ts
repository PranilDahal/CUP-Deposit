import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-transhistory',
  templateUrl: './transhistory.component.html',
  styleUrls: ['./transhistory.component.css']
})
export class TranshistoryComponent implements OnInit, OnChanges {

  @Input() selectedbyuser: string;
  transhistory: any = [];
  constructor(public rest: RestapiService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.getTransHistoryByAccountNumber(this.selectedbyuser);
  }

  // when the uses selects a new account, detect the changes and grab the curret value, and update the componenet with new transaction history
  ngOnChanges(changes: SimpleChanges) {
    console.log(changes)
    this.getTransHistoryByAccountNumber(changes['selectedbyuser'].currentValue);
  }

  getTransHistoryByAccountNumber(acctnumber: string) {
    this.transhistory = [];

    this.rest.getAccountTransHistory(acctnumber).subscribe((data: {}) => {
      this.transhistory = data;
    });
  }

}
