package com.upgrade.tests;

import com.upgrade.pojos.LoanAccountResponse;
import com.upgrade.pojos.LoanInReviewResponse;
import com.upgrade.pojos.LoginRequest;
import com.upgrade.pojos.UserResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.UUID;


@Log4j
public class LoginApiTest extends AbstractTest {

    private String email = "coding.challenge.login@upgrade.com";
    private String password = "On$3XcgsW#9q";
    private String url = "https://credapi.credify.tech/api/brportorch/";
    private String email2 = "coding.challenge.wronglogin@upgrade.com";
    private String password2 = "12345678";
    /*
        Please refer README.md for more details on APT Test
    */

    @Test
    public void loginToAccountTest() {
        LoginRequest loginRequestPayload = LoginRequest.builder()
                .username(email)
                .password(password)
                .build();

        Response response = apiRequest()
                .addHeader("x-cf-corr-id", UUID.randomUUID().toString())
                .addHeader("x-cf-source-id", "coding-challenge")
                .setContentType(ContentType.JSON)
                .setRequestUrl(url + "v2/login")
                .post(loginRequestPayload, 200)
                .getResponse();

        UserResponse response1 = response.as(UserResponse.class);
        // Validate some fields in addition to the one required (product type)
        Assert.assertEquals(response1.getFirstName(), "Ian");
        for (LoanInReviewResponse loan: response1.getLoansInReview()){
            Assert.assertEquals(loan.getProductType(),"PERSONAL_LOAN" );
            Assert.assertEquals(loan.getId(),"9545966" );
        }
        for (LoanAccountResponse loan: response1.getLoanAccountSummaryAto()){
            Assert.assertEquals(loan.getDaysPastDue(),0);
        }

    }

    @Test
    public void invalidLoginToAccountTest() {
        /*Building the login request with invalid body (username(mail) and password)*/
        LoginRequest loginRequestPayload2 = LoginRequest.builder()
                .username(email2)
                .password(password2)
                .build();


        /* Validation of status 401 for invalid user */
        Response response2 = apiRequest()
                .addHeader("x-cf-corr-id", UUID.randomUUID().toString())
                .addHeader("x-cf-source-id", "coding-challenge")
                .setContentType(ContentType.JSON)
                .setRequestUrl(url + "v2/login")
                .post(loginRequestPayload2, 401)
                .getResponse();
    }

}
