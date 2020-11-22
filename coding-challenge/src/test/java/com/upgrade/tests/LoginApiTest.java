package com.upgrade.tests;

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

    /*LoginRequest.builder() is like a builder of everything inside Borrower pojo*/
    @Test
    public void loginToAccountTest() {
        /*Building the login request with body (username(mail) and password)*/
        LoginRequest loginRequestPayload = LoginRequest.builder()
                .username(email)
                .password(password)
                .build();

        /*Making request and getting the response given assigned to response object
         * Validation of status 200 for valid user*/
        Response response = apiRequest()
                .addHeader("x-cf-corr-id", UUID.randomUUID().toString())
                .addHeader("x-cf-source-id", "coding-challenge")
                .setContentType(ContentType.JSON)
                .setRequestUrl(url + "v2/login")
                .post(loginRequestPayload, 200)
                .getResponse();
        //Check if it is needed or status code is already validated on post(). Assert.assertEquals(response.getStatusCode(),200);

        System.out.println(response.asString());
        /*Response1 will load the json data(got from request) of response done above at json data(pojo UserResponse) of response1*/
        UserResponse response1 = response.as(UserResponse.class);
        // Validate product type value
        Assert.assertEquals(response1.loanInReviewGetter("productType"), "PERSONAL_LOAN");
        Assert.assertEquals(response1.getFirstName(), "Ian");
    }

    @Test
    public void invalidLoginToAccountTest() {
        /*Building the login request with body (username(mail) and password)*/
        LoginRequest loginRequestPayload2 = LoginRequest.builder()
                .username(email2)
                .password(password2)
                .build();

        /*Making request and getting the response given assigned to response object
         * Validation of status 200 for valid user*/
        Response response2 = apiRequest()
                .addHeader("x-cf-corr-id", UUID.randomUUID().toString())
                .addHeader("x-cf-source-id", "coding-challenge")
                .setContentType(ContentType.JSON)
                .setRequestUrl(url + "v2/login")
                .post(loginRequestPayload2, 401)
                .getResponse();
        //Check if it is needed or status code is already validated on post(). Assert.assertEquals(response2.getStatusCode(),401);
        System.out.println(response2.asString());

    }
}
