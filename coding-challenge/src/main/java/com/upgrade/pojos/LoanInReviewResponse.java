package com.upgrade.pojos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LoanInReviewResponse {

    private String id;
    private String uuid;
    private String status;
    private String productType;
    private String sourceSystem;
    private boolean hasOpenBackendCounter;
    private String purpose;
    private Date createDate;
    private List postIssuanceStatus;
    private List addon;

}
