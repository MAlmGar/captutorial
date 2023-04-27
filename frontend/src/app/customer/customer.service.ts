import { Injectable } from '@angular/core';
import { Observable, catchError, of, throwError } from 'rxjs';
import { Customer } from './model/Customer';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(
    private http: HttpClient
  ) { }

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>('http://localhost:8080/customer');
  }

  saveCustomer(customer: Customer): Observable<Customer> {
    let url = 'http://localhost:8080/customer';
    if (customer.id != null) url += '/'+customer.id;

    return this.http.put<Customer>(url, customer);
  }

  deleteCustomer(idCustomer : number): Observable<any> {
    return this.http.delete('http://localhost:8080/customer/'+idCustomer);
  }
}