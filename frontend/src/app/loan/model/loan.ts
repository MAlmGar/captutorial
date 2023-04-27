import { Customer } from "src/app/customer/model/Customer";
import { Game } from "src/app/game/model/Game";

export class Loan {
    id: number;
    customer: Customer;
    game: Game;
    loanDate: Date;
    returnDate: Date;
}