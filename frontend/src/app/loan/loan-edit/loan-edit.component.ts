import { Component, OnInit, Inject } from '@angular/core';
import { Loan } from '../model/loan';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LoanService } from '../loan.service';
import { CustomerService } from 'src/app/customer/customer.service';
import { Customer } from 'src/app/customer/model/Customer';
import { GameService } from 'src/app/game/game.service';
import { Game } from 'src/app/game/model/Game';
import { FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-loan-edit',
  templateUrl: './loan-edit.component.html',
  styleUrls: ['./loan-edit.component.scss']
})
export class LoanEditComponent implements OnInit {
  loan : Loan;
  customers: Customer[];
  games: Game[];
  loanDate = new FormControl(new Date());
  returnDate = new FormControl(new Date());
  inputDate: Date;

  constructor(
    public dialogRef: MatDialogRef<LoanEditComponent>,
    @Inject(MAT_DIALOG_DATA) 
    public data: any,
    private loanService: LoanService,
    private customerService: CustomerService,
    private gameService: GameService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.loan = new Loan();

    this.gameService.getGames().subscribe(
      games => {
          this.games = games;
      }
    )

    this.customerService.getCustomers().subscribe(
      customers => {
          this.customers = customers;
      }
    )
  }

  onSave() {

    let inputDate = new Date();
    inputDate.setDate(this.loan.loanDate.getDate() + 14);
    
    if (this.loan.returnDate < this.loan.loanDate){
      this.snackBar.open('La fecha de devolución no puede ser anterior a la fecha de salida', '', {
        duration: 3000
      });
    }
    else if (this.loan.returnDate > inputDate) {
      this.snackBar.open('El período máximo de préstamo es de 14 días', '', {
        duration: 3000
      });
    }
    else{
      this.loanService.saveLoan(this.loan).subscribe(result =>  {
        this.dialogRef.close();
      }); 
    }
  }  

  onClose() {
    this.dialogRef.close();
  }

}
