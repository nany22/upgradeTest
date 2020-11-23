package com.upgrade.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LoanAccountResponse {

    private List loanAccountNumber;
    private List postIssuanceLoanStatus;
    private int daysPastDue;
    private List loanProductType;

}
