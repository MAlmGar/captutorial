package com.ccsw.tutorial.loan.model;

import java.util.Date;

import com.ccsw.tutorial.customer.model.Customer;
import com.ccsw.tutorial.game.model.Game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "loan_date", nullable = false)
    private Date loanDate;

    @Column(name = "return_date", nullable = false)
    private Date returnDate;

    public Long getId() {

        return this.id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Game getGame() {

        return this.game;
    }

    public void setGame(Game game) {

        this.game = game;
    }

    public Customer getCustomer() {

        return this.customer;
    }

    public void setCustomer(Customer customer) {

        this.customer = customer;
    }

    public Date getLoanDate() {

        return this.loanDate;
    }

    public void setLoanDate(Date loanDate) {

        this.loanDate = loanDate;
    }

    public Date getReturnDate() {

        return this.returnDate;
    }

    public void setReturnDate(Date returnDate) {

        this.returnDate = returnDate;
    }

}