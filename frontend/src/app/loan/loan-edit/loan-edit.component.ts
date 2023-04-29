import { Component, OnInit, Inject } from '@angular/core';
import { Loan } from '../model/loan';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LoanService } from '../loan.service';

@Component({
  selector: 'app-loan-edit',
  templateUrl: './loan-edit.component.html',
  styleUrls: ['./loan-edit.component.scss']
})
export class LoanEditComponent implements OnInit {
  loan : Loan;

  constructor(
      public dialogRef: MatDialogRef<LoanEditComponent>,
      @Inject(MAT_DIALOG_DATA) public data: any,
      private loanService: LoanService
  ) { }

  ngOnInit(): void {
          this.loan = new Loan();
  }

  onSave() {
      this.loanService.saveLoan(this.loan).subscribe(result =>  {
          this.dialogRef.close();
      }); 
  }  

  onClose() {
      this.dialogRef.close();
  }

}
