import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

const endpoint = 'http://localhost:8080/api/v2/';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})

// This is where we define all the API Calls
export class RestapiService {

  constructor(private http: HttpClient) { }

  private extractData(res: Response) {
    let body = res;
    return body || { };
  }

  getAllNonmatchingAccounts(): Observable<any> {
    return this.http.get(endpoint + 'nonmatching/all').pipe(
      map(this.extractData));
  }

  getBalanceByContact(): Observable<any> {
    return this.http.get(endpoint + 'balances/contact').pipe(
      map(this.extractData));
  }

  getBalanceByPlan(): Observable<any> {
    return this.http.get(endpoint + 'balances/plan').pipe(
      map(this.extractData));
  }

  getAllAccountBalances(): Observable<any> {
    return this.http.get(endpoint + 'accountbalances/all').pipe(
      map(this.extractData));
  }

  searchBbcByName(entityName:string): Observable<any> {
    return this.http.get(endpoint + 'balances/search/bbc/'+entityName).pipe(
      map(this.extractData));
  }
  
  getAccountTransHistory(acctNumber:string): Observable<any> {
    return this.http.get(endpoint + 'accountTransHistory/'+acctNumber).pipe(
      map(this.extractData));
  }

  searchBbpByNumber(entityNumber:string): Observable<any> {
    return this.http.get(endpoint + 'balances/search/bbp/'+entityNumber).pipe(
      map(this.extractData));
  }

  getPlanTransHistory(plannumber:string): Observable<any> {
    return this.http.get(endpoint + 'planTransHistory/'+plannumber).pipe(
      map(this.extractData));
  }
  
  searchAllAccountBalancesByDistrict(district:string): Observable<any> {
    return this.http.get(endpoint + 'accountbalances/accountsbydistrict/'+district).pipe(
      map(this.extractData));
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
