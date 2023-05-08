package com.ccsw.tutorial.loan;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

public interface LoanService {

    Page<Loan> findPage(LoanSearchDto dto, String titleGame, String nameCustomer, String inputDate)
            throws ParseException;

    void save(Long id, LoanDto dto) throws Exception;

    void delete(Long id) throws Exception;

    List<Loan> findAll();

}