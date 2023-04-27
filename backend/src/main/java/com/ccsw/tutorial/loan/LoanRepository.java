package com.ccsw.tutorial.loan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorial.loan.model.Loan;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    Page<Loan> findAll(Pageable pageable);

}
