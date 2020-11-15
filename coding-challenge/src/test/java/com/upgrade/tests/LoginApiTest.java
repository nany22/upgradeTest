package com.upgrade.tests;

import com.upgrade.pojos.LoginRequest;
import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j;
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

        apiRequest()
                .addHeader("x-cf-corr-id", UUID.randomUUID().toString())
                .addHeader("x-cf-source-id", "coding-challenge")
                .setContentType(ContentType.JSON)
                .setRequestUrl(url + "v2/login")
                .post(loginRequestPayload, 200)
                .getResponse();
    }

}
