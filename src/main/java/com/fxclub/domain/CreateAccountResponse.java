package com.fxclub.domain;

import com.fxclub.domain.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateAccountResponse extends CommonResponse {

    private Number id;

}
