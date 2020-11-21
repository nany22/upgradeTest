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

        System.out.println(response.asString());
        /*Response1 will load the json data(got from request) of response done above at json data(pojo UserResponse) of response1*/
        UserResponse response1 = response.as(UserResponse.class);
        Assert.assertEquals(response1.getFirstName(), "Ian");
    }

}
