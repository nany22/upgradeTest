package com.upgrade.pojos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.util.*;

@Builder
public class UserResponse {

    @Setter @Getter String firstName;
    @Setter @Getter String userId;
    @Setter @Getter String userUuid;
    @Setter @Getter String authenticationLevel;
    @Setter @Getter String actorId;
    @Setter @Getter ArrayList loanApplications;
    Dictionary<String, String> loanInReview;;

    //private Map<String,String> loanInReview;

    public void loanInReviewSetter(String key, String value){
        if(loanInReview.containsKey(key)) {
            loanInReview[key] = value;
        }
    }
    public String loanInReviewGetter(String key){
        String result = null;
        if (loanInReview.containsKey(key)){
            result = loanInReview[key];
        }
        return result;
    }
}
