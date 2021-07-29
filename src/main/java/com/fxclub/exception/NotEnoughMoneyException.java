package com.fxclub.exception;

import java.math.BigDecimal;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException(Integer id, BigDecimal amount) {
        super(String.format("There is no enough money on account %s: %s", id, amount));
    }
}
