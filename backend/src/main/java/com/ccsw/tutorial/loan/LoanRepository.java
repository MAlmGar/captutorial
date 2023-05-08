package com.ccsw.tutorial.loan;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorial.loan.model.Loan;

public interface LoanRepository extends CrudRepository<Loan, Long>, JpaSpecificationExecutor<Loan> {

    @Override
    @EntityGraph(attributePaths = { "game", "customer", "loanDate", "returnDate" })
    List<Loan> findAll(Specification<Loan> spec);

    @Override
    @EntityGraph(attributePaths = { "game", "customer", "loanDate", "returnDate" })
    Page<Loan> findAll(Specification<Loan> spec, Pageable pageable);
}
