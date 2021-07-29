package com.fxclub.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountWithdrawalRequest {

    private Integer id;
    private BigDecimal amount;

}
