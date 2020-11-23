package com.upgrade.pojos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    private String firstName;
    private String userId;
    private String userUuid;
    private String authenticationLevel;
    private String actorId;
    private List loanApplications;
    private List<LoanInReviewResponse> loansInReview;;
    private List<LoanAccountResponse> loanAccountSummaryAto;

}
