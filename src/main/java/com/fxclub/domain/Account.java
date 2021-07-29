package com.fxclub.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Table
@Entity

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @SequenceGenerator(name = "account_id_seq", sequenceName = "account_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_seq")
    @Column(name = "id", columnDefinition = "serial")
    private Integer id;

    private BigDecimal balance;
}
