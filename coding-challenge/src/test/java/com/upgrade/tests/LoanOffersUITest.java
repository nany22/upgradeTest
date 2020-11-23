package com.upgrade.tests;

import com.upgrade.pages.*;
import com.upgrade.pojos.Borrower;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
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

        /* Assigning landingPage to a SelectOfferPage object to make it
        easier to save values (taken of SelectOfferPage) after login */
        SelectOfferPage firstLogin = landingPage
                .gotoLandingPage(url)
                .enterLoanDetails(borrower)
                .enterContactDetails(borrower)
                .enterIncomeDetails(borrower)
                .enterLoginDetails(borrower);

        //save page values for first login in the `/offer-page`
        String firstAmount = firstLogin.getLoanAmount();
        String firstTerm = firstLogin.getLoanTerm();
        String firstMonthlyPayment = firstLogin.getMonthlyPayment();
        String firstInterestRate = firstLogin.getLoanInterestRate();
        String firstAPR = firstLogin.getLoanAPR();
        firstLogin.clickSignOut();

        /* Assigning landingPage to a SignInPage object to make it
        easier to save values (taken of SignInPage) after login */
        SignInPage signInPage = new SignInPage(getDriver());
        SelectOfferPage secondLogin = signInPage
                .gotoSignInPage(url)
                .signIn(borrower);

        //save page values for second login in the `/signing page`
        String secondAmount = secondLogin.getLoanAmount();
        String secondTerm = firstLogin.getLoanTerm();
        String secondMonthlyPayment = firstLogin.getMonthlyPayment();
        String secondInterestRate = firstLogin.getLoanInterestRate();
        String secondAPR = firstLogin.getLoanAPR();

        //Assertions between xpected and current result after re-ogin
        Assert.assertEquals(secondAmount, firstAmount);
        Assert.assertEquals(secondTerm, firstTerm);
        Assert.assertEquals(secondMonthlyPayment, firstMonthlyPayment);
        Assert.assertEquals(secondInterestRate, firstInterestRate);
        Assert.assertEquals(secondAPR, firstAPR);

        secondLogin.clickSignOut();
    }

    /*
        Please refer README.md for more details on
        Case # 2  : Loan rejected for low annual income
    */

    @Test
    public void validateDeclineLoanTest() {
        Borrower borrower = getTestBorrowerBadCredit();
        LandingPage landingPage = new LandingPage(getDriver());

        AdversePage adversePage = landingPage
                .gotoLandingPage(url)
                .enterLoanDetails(borrower)
                .enterContactDetails(borrower)
                .enterIncomeDetails(borrower)
                .enterLoginDetails(borrower);

        //Validate loan is rejected by message
        String declinedMessage = adversePage.getDeclinedMessage();
        Assert.assertTrue(declinedMessage.contains("We´re sorry, you were not approved."));

        //Validate current page includes integer as loanID (it is done using regex)
        adversePage.adverseLearnMoreLink();
        String url = getDriver().getCurrentUrl();
        boolean isAMatch = url.matches(".*/portal/product/[0-9]+/documents");
        Assert.assertTrue(isAMatch, "url doesn't not match");

        //Validate specific PDF link exists
        DocumentsPage documentsPage = new DocumentsPage(getDriver());
        boolean linkIsPresent = documentsPage.findLinkDocument("Adverse Action Notice.pdf");
        Assert.assertEquals(linkIsPresent, true);
    }

}
