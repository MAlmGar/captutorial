import { Injectable } from '@angular/core';
import { Pageable } from '../core/model/page/Pageable';
import { LoanPage } from './model/LoanPage';
import { Observable } from 'rxjs';
import { Loan } from './model/loan';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoanService {

  constructor(
    private http: HttpClient
  ) { }

  getLoans(pageable: Pageable): Observable<LoanPage> {
    return this.http.post<LoanPage>('http://localhost:8080/loan', {pageable:pageable});
    }

  saveLoan(loan: Loan): Observable<void> {
    let url = 'http://localhost:8080/loan';
    return this.http.put<void>(url, loan);
  }

  deleteLoan(idLoan: number): Observable<void> {
    return this.http.delete<void>('http://localhost:8080/loan/'+idLoan);
  }

  getAllLoans(): Observable<Loan[]> {
    return this.http.get<Loan[]>('http://localhost:8080/loan');
  }
}