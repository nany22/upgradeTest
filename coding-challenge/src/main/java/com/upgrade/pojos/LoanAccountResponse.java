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
    //Structure is unknown so it is a list of object
    private List<Object> loanAccountNumber;
    private List<Object> postIssuanceLoanStatus;
    private int daysPastDue;
    private List<Object> loanProductType;

}
