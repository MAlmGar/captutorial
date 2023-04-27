package com.ccsw.tutorial.loan;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
    public Loan get(Long id) {

        return this.loanRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Loan> findPage(LoanSearchDto dto) {

        return this.loanRepository.findAll(dto.getPageable().getPageable());
    }

    @Override
    public void save(Long id, LoanDto data) {

        Loan loan;

        if (id == null) {
            loan = new Loan();
        } else {
            loan = this.loanRepository.findById(id).orElse(null);
        }

        BeanUtils.copyProperties(data, loan, "id", "customer", "game");

        this.loanRepository.save(loan);
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
}
