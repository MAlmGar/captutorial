package com.ccsw.tutorial.loan.model;

import java.time.LocalDateTime;

import com.ccsw.tutorial.customer.model.CustomerDto;
import com.ccsw.tutorial.game.model.GameDto;

public class LoanDto {

    private Long id;

    private GameDto game;

    private CustomerDto customer;

    private LocalDateTime loanDate;

    private LocalDateTime returnDate;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameDto getGame() {
        return this.game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

}
