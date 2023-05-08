package com.ccsw.tutorial.loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.customer.CustomerService;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    GameService gameService;

    @Autowired
    CustomerService customerService;

    @Override
    public Page<Loan> findPage(LoanSearchDto dto, String titleGame, String nameCustomer, String inputDate)
            throws ParseException {

        LoanSpecification nameGameSpec = new LoanSpecification(new SearchCriteria("game.title", ":", titleGame));
        LoanSpecification nameCustomerSpec = new LoanSpecification(
                new SearchCriteria("customer.name", ":", nameCustomer));

        Specification<Loan> spec;

        if (inputDate != null) {
            Date formatDate = new SimpleDateFormat("yyyy-MM-dd").parse(inputDate);
            LoanSpecification loanDateSpec = new LoanSpecification(new SearchCriteria("loanDate", "<", formatDate));
            LoanSpecification returnDateSpec = new LoanSpecification(new SearchCriteria("returnDate", ">", formatDate));
            spec = Specification.where(nameGameSpec).and(nameCustomerSpec).and(loanDateSpec).and(returnDateSpec);
        } else {
            spec = Specification.where(nameGameSpec).and(nameCustomerSpec);
        }
        return this.loanRepository.findAll(spec, dto.getPageable().getPageable());
    }

    @Override
    public void save(Long id, LoanDto dto) throws Exception {

        Loan loan = new Loan();

        BeanUtils.copyProperties(dto, loan, "id", "customer", "game");

        loan.setGame(gameService.get(dto.getGame().getId()));
        loan.setCustomer(customerService.get(dto.getCustomer().getId()));

        if (this.gameValidation(loan)) {
            throw new Exception("El juego no está disponible para estas fechas");
        } else if (this.customerValidation(loan)) {
            throw new Exception("Un cliente no puede tener más de dos juegos prestados en la misma fecha");
        }

        else {
            this.loanRepository.save(loan);
        }
    }

    @Override
    public void delete(Long id) throws Exception {

        if (this.loanRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        this.loanRepository.deleteById(id);
    }

    public List<Loan> findAll() {

        return (List<Loan>) this.loanRepository.findAll();
    }

    private boolean gameValidation(Loan loan) {
        LoanSpecification gameSpec = new LoanSpecification(new SearchCriteria("game.id", ":", loan.getGame().getId()));

        LoanSpecification loanDateLessThanLoanDateSpec = new LoanSpecification(
                new SearchCriteria("loanDate", "<", loan.getLoanDate()));
        LoanSpecification returnDateGreaterThanLoanDateSpec = new LoanSpecification(
                new SearchCriteria("returnDate", ">", loan.getLoanDate()));

        LoanSpecification loanDateLessThanReturnDateSpec = new LoanSpecification(
                new SearchCriteria("loanDate", "<", loan.getReturnDate()));
        LoanSpecification returnDateGreaterThanReturnDateSpec = new LoanSpecification(
                new SearchCriteria("returnDate", ">", loan.getReturnDate()));

        LoanSpecification loanDateGreaterThanLoanDateSpec = new LoanSpecification(
                new SearchCriteria("loanDate", ">", loan.getLoanDate()));
        LoanSpecification returnDateLessThanReturnDateSpec = new LoanSpecification(
                new SearchCriteria("returnDate", "<", loan.getReturnDate()));

        Specification<Loan> specGame1 = Specification.where(gameSpec).and(loanDateLessThanLoanDateSpec)
                .and(returnDateGreaterThanLoanDateSpec);
        Specification<Loan> specGame2 = Specification.where(gameSpec).and(loanDateLessThanReturnDateSpec)
                .and(returnDateGreaterThanReturnDateSpec);
        Specification<Loan> specGame3 = Specification.where(gameSpec).and(loanDateGreaterThanLoanDateSpec)
                .and(returnDateLessThanReturnDateSpec);

        List<Loan> loansGame1 = this.loanRepository.findAll(specGame1);
        List<Loan> loansGame2 = this.loanRepository.findAll(specGame2);
        List<Loan> loansGame3 = this.loanRepository.findAll(specGame3);

        return loansGame1.size() != 0 || loansGame2.size() != 0 || loansGame3.size() != 0;
    }

    private boolean customerValidation(Loan loan) {
        LoanSpecification customerSpec = new LoanSpecification(
                new SearchCriteria("customer.id", ":", loan.getCustomer().getId()));

        LoanSpecification loanDateLessThanLoanDateSpec = new LoanSpecification(
                new SearchCriteria("loanDate", "<", loan.getLoanDate()));
        LoanSpecification returnDateGreaterThanLoanDateSpec = new LoanSpecification(
                new SearchCriteria("returnDate", ">", loan.getLoanDate()));

        LoanSpecification loanDateLessThanReturnDateSpec = new LoanSpecification(
                new SearchCriteria("loanDate", "<", loan.getReturnDate()));
        LoanSpecification returnDateGreaterThanReturnDateSpec = new LoanSpecification(
                new SearchCriteria("returnDate", ">", loan.getReturnDate()));

        LoanSpecification loanDateGreaterThanLoanDateSpec = new LoanSpecification(
                new SearchCriteria("loanDate", ">", loan.getLoanDate()));
        LoanSpecification returnDateLessThanReturnDateSpec = new LoanSpecification(
                new SearchCriteria("returnDate", "<", loan.getReturnDate()));

        Specification<Loan> specCustomer1 = Specification.where(customerSpec).and(loanDateLessThanLoanDateSpec)
                .and(returnDateGreaterThanLoanDateSpec);
        Specification<Loan> specCustomer2 = Specification.where(customerSpec).and(loanDateLessThanReturnDateSpec)
                .and(returnDateGreaterThanReturnDateSpec);
        Specification<Loan> specCustomer3 = Specification.where(customerSpec).and(loanDateGreaterThanLoanDateSpec)
                .and(returnDateLessThanReturnDateSpec);

        List<Loan> loansCustomer1 = this.loanRepository.findAll(specCustomer1);
        List<Loan> loansCustomer2 = this.loanRepository.findAll(specCustomer2);
        List<Loan> loansCustomer3 = this.loanRepository.findAll(specCustomer3);

        loansCustomer1.addAll(loansCustomer2);
        loansCustomer1.addAll(loansCustomer3);

        List<Loan> loansWithoutDuplicates = new ArrayList<>(new HashSet<>(loansCustomer1));

        return loansWithoutDuplicates.size() >= 2;
    }
}
