package com.fxclub.dao;

import com.fxclub.domain.Account;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> findById(Integer id);

    Optional<Account> findByIdForManipulation(Integer id);

    Account save(Account account);
}
