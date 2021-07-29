package com.fxclub.dao.impl;

import com.fxclub.dao.AccountRepository;
import com.fxclub.domain.Account;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Account> findById(Integer id) {
        Account account = null;
        try {
            account = jdbcTemplate.queryForObject("SELECT id, balance FROM account WHERE id = ?", this::mapAccount, id);
        } catch (DataAccessException ignored) {
        }

        return Optional.ofNullable(account);
    }

    @Override
    public Optional<Account> findByIdForManipulation(Integer id) {
        Account account = null;
        try {
            account = jdbcTemplate.queryForObject("SELECT id, balance FROM account WHERE id = ? FOR UPDATE", this::mapAccount, id);
        } catch (DataAccessException ignored) {
        }

        return Optional.ofNullable(account);
    }

    @Override
    public Account save(Account account) {

        String sql = account.getId() != null ? "INSERT INTO account (id, balance) VALUES(?, ?) ON CONFLICT (id) DO UPDATE SET balance = ? RETURNING id" :
                "INSERT INTO account (balance) VALUES(?) RETURNING id";

        Integer id = account.getId() != null ? jdbcTemplate.queryForObject(sql, Integer.class, account.getId(), account.getBalance(), account.getBalance()) :
                jdbcTemplate.queryForObject(sql, Integer.class, account.getBalance());

        return new Account(id, account.getBalance());
    }

    private Account mapAccount(ResultSet rs, int rowNum) throws SQLException {
        Account rsAccount = new Account();
        rsAccount.setId(rs.getInt("id"));
        rsAccount.setBalance(rs.getBigDecimal("balance"));
        return rsAccount;
    }
}
