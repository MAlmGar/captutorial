import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Loan } from '../model/loan';
import { LoanService } from '../loan.service';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { LoanEditComponent } from '../loan-edit/loan-edit.component';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';
import { Game } from 'src/app/game/model/Game';
import { Customer } from 'src/app/customer/model/Customer';
import { LoanPage } from '../model/LoanPage';
import { GameService } from '../../game/game.service';
import { CustomerService } from 'src/app/customer/customer.service';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-loan-list',
  templateUrl: './loan-list.component.html',
  styleUrls: ['./loan-list.component.scss']
})
export class LoanListComponent implements OnInit {

  pageNumber: number = 0;
  pageSize: number = 5;
  totalElements: number = 0;

  dataSource = new MatTableDataSource<Loan>();
  displayedColumns: string[] = ['id', 'titleGame', 'nameCustomer', 'loanDate', 'returnDate', 'action'];

  games : Game[];
  customers : Customer[];
  loans: LoanPage;
  filterGame: Game;
  filterCustomer: Customer;
  filterDate: Date;

  constructor(
    private loanService: LoanService,
    private gameService : GameService,
    private customerService : CustomerService,
    public dialog: MatDialog,
  ) {}

  ngOnInit(): void {
    this.loadPage();

    this.gameService.getGames().subscribe(
      games => this.games = games
    );

    this.customerService.getCustomers().subscribe(
      customers => this.customers = customers
    );
  }

  loadPage(event?: PageEvent) {

    let pageable : Pageable =  {
        pageNumber: this.pageNumber,
        pageSize: this.pageSize,
        sort: [{
            property: 'id',
            direction: 'ASC'
        }]
    }

    if (event != null) {
        pageable.pageSize = event.pageSize
        pageable.pageNumber = event.pageIndex;
    }

    this.loanService.getLoans(pageable).subscribe(data => {
        this.dataSource.data = data.content;
        this.pageNumber = data.pageable.pageNumber;
        this.pageSize = data.pageable.pageSize;
        this.totalElements = data.totalElements;
    });
  }

  onCleanFilter(): void {
    this.filterGame = null;
    this.filterCustomer = null;
    this.filterDate = null; 

    this.onSearch();
  }

  onSearch(): void {

    let pageable : Pageable =  {
      pageNumber: this.pageNumber,
      pageSize: this.pageSize,
      sort: [{
          property: 'id',
          direction: 'ASC'
      }]
    };
    let titleGame = this.filterGame != null ? this.filterGame.title : null;
    let nameCustomer = this.filterCustomer != null ? this.filterCustomer.name : null;
    let inputDate = this.filterDate != null ? formatDate(this.filterDate, 'yyyy-MM-dd', 'en-US') : null;

    this.loanService.getLoans(pageable, titleGame, nameCustomer, inputDate).subscribe(
        data => {
          this.dataSource.data = data.content;
          this.pageNumber = data.pageable.pageNumber;
          this.pageSize = data.pageable.pageSize;
          this.totalElements = data.totalElements;
      });
  }

  createLoan() {      
    const dialogRef = this.dialog.open(LoanEditComponent, {
        data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
        this.ngOnInit();
    });      
  }  

  deleteLoan(loan: Loan) {    
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {
        data: { title: "Eliminar préstamo", description: "Atención si borra el préstamo se perderán sus datos.<br> ¿Desea eliminar el préstamo?" }
    });

    dialogRef.afterClosed().subscribe(result => {
        if (result) {
            this.loanService.deleteLoan(loan.id).subscribe(result =>  {
                this.ngOnInit();
            }); 
        }
    });
  } 
} 
