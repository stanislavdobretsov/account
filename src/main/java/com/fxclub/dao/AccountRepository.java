package com.fxclub.dao;

import com.fxclub.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
