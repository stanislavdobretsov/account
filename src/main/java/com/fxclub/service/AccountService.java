package com.fxclub.service;

import com.fxclub.exception.NotEnoughMoneyException;
import com.fxclub.dao.AccountRepository;
import com.fxclub.domain.Account;
import com.fxclub.exception.AccountNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account createAccount() {
        return accountRepository.save(Account.builder().balance(new BigDecimal(0)).build());
    }

    public Optional<Account> findById(Integer id) {
        return accountRepository.findById(id);
    }

    @Transactional
    public void deposit(Integer id, BigDecimal amount) throws AccountNotFoundException {
        log.debug("Attempt to deposit {} on account {}", amount, id);
        Account account = accountRepository
                .findByIdForManipulation(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

    @Transactional
    public void withdraw(Integer id, BigDecimal amount) throws AccountNotFoundException, NotEnoughMoneyException {
        log.debug("Attempt to withdraw {} from account {}", amount, id);
        Account account = accountRepository
                .findByIdForManipulation(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        if (account.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) <= 0) {
            throw new NotEnoughMoneyException(id, amount);
        }
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }
}
