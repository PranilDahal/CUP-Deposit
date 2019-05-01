import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { NonmatchingaccountsComponent } from './nonmatchingaccounts/nonmatchingaccounts.component';

import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HomepageComponent } from './homepage/homepage.component';
import { HeaderComponent } from './header/header.component';
import { BalancebyplanComponent } from './balancebyplan/balancebyplan.component';
import { BalancebycontactComponent } from './balancebycontact/balancebycontact.component';
import { AllaccountbalancesComponent } from './allaccountbalances/allaccountbalances.component';
import { TranshistoryComponent } from './transhistory/transhistory.component';
import { PlanhistoryComponent } from './planhistory/planhistory.component';
import { HttpService } from './service/http.service';

const appRoutes: Routes = [

  {
    path: 'home',
    component: HomepageComponent,
    data: { title: 'Drawdown Interface' }
  },

  {
    path: 'nonmatching',
    component: NonmatchingaccountsComponent,
    data: {title: 'Account Alerts'}
  },

  {
    path: 'balance-by-plan',
    component: BalancebyplanComponent,
    data: {title: 'Balance by Plan'}
  },

  {
    path: 'balance-by-contact',
    component: BalancebycontactComponent,
    data: {title: 'Balance by Contact'}
  },

  {
    path: 'all-account-balances',
    component: AllaccountbalancesComponent,
    data: {title: 'All Account Balances'}
  },

  { path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  }

];

@NgModule({
  declarations: [
    AppComponent,
    NonmatchingaccountsComponent,
    HomepageComponent,
    HeaderComponent,
    BalancebyplanComponent,
    BalancebycontactComponent,
    AllaccountbalancesComponent,
    TranshistoryComponent,
    PlanhistoryComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    FormsModule,
    BrowserModule,
    HttpClientModule
  ],
  providers: [HttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
