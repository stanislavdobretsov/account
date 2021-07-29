package com.fxclub.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDepositRequest {

    private Integer id;
    private BigDecimal amount;

}
