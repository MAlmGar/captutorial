import { Injectable } from '@angular/core';
import { LoanPage } from './model/LoanPage';
import { Observable } from 'rxjs';
import { Loan } from './model/loan';
import { HttpClient } from '@angular/common/http';
import { Pageable } from '../core/model/page/Pageable';

@Injectable({
  providedIn: 'root'
})
export class LoanService {

  constructor(
    private http: HttpClient
  ) { }

  getLoans(pageable: Pageable, titleGame?: String, nameCustomer?: String, inputDate?: string): Observable<LoanPage> {
    return this.http.post<LoanPage>(this.composeFindUrl(titleGame, nameCustomer, inputDate), {pageable:pageable});
    }

  saveLoan(loan: Loan): Observable<void> {
    let url = 'http://localhost:8080/loan';

    if (loan.id != null) {
      url += '/'+loan.id;
    }

    return this.http.put<void>(url, loan);
  }

  private composeFindUrl(titleGame?: String, nameCustomer?: String, inputDate?: string) : string {
      let params = '';

      if (titleGame != null) {
          params += 'titleGame='+titleGame;
      }

      if (nameCustomer != null) {
          if (params != '') params += "&";
          params += "nameCustomer="+nameCustomer;
      }

      if (inputDate != null) {
        if (params != '') params += "&";
        params += "inputDate="+inputDate;
    }

      let url = 'http://localhost:8080/loan'

      if (params == '') return url;
      else return url + '?'+params;
  }

  deleteLoan(idLoan: number): Observable<void> {
    return this.http.delete<void>('http://localhost:8080/loan/'+idLoan);
  }

  // getAllLoans(): Observable<Loan[]> {
  //   return this.http.get<Loan[]>('http://localhost:8080/loan');
  // }
}