package com.upgrade.tests;

import com.upgrade.pages.LandingPage;
import com.upgrade.pages.SignInPage;
import com.upgrade.pojos.Borrower;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Test;

import static com.upgrade.tests.BorrowerDataProvider.*;

@Log4j
public class LoanOffersUITest extends AbstractTest {
    private static final String url = "https://www.credify.tech";

    /*
        Please refer README.md for more details on
        Case # 1 : Validate offers after re-login
    */

    @Test
    public void validateOffersTest() {
        Borrower borrower = getTestBorrowerGoodCredit();
        LandingPage landingPage = new LandingPage(getDriver());

        //Capture offer details in the Offers page
        landingPage
                .gotoLandingPage(url)
                .enterLoanDetails(borrower)
                .enterContactDetails(borrower)
                .enterIncomeDetails(borrower)
                .enterLoginDetails(borrower)
                .clickSignOut();


        //Validate the offer details after login
        SignInPage signInPage = new SignInPage(getDriver());
        signInPage
                .gotoSignInPage(url)
                .signIn(borrower)
                .clickSignOut();
    }

    /*
        Please refer README.md for more details on
        Case # 2  : Loan rejected for low annual income
    */

    @Test
    public void validateDeclineLoanTest() {
        // Implement Case # 2
        Borrower borrower = getTestBorrowerBadCredit();
        LandingPage landingPage = new LandingPage(getDriver());

        landingPage
                .gotoLandingPage(url)
                .enterLoanDetails(borrower)
                .enterContactDetails(borrower)
                .enterIncomeDetails(borrower)
                .enterLoginDetails(borrower);

        //Validate the loan is declined
    }

}
