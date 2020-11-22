package com.upgrade.tests;

import com.upgrade.pages.*;
import com.upgrade.pojos.Borrower;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

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

        //Assertions between first (expected result at the end of asserts) and second login
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

        landingPage
                .gotoLandingPage(url)
                .enterLoanDetails(borrower)
                .enterContactDetails(borrower)
                .enterIncomeDetails(borrower)
                .enterLoginDetails(borrower);

        //After loginDetails, the new instance is the new page /funnel/AdversePage
        AdversePage adversePage = new AdversePage(getDriver());

        //Validate loan is rejected by message
        String declinedMessage = adversePage.getDeclinedMessage();
        Assert.assertTrue(declinedMessage.contains("We´re sorry, you were not approved."));

        //Validate current page includes loan id (WIP get loanID)
        String expectedDocumentsPath = adversePage.adverseAction(url);
        String actualDocumentsPath = getDriver().getCurrentUrl();
        Assert.assertEquals(actualDocumentsPath, expectedDocumentsPath);

        //Validate specific PDF link exists (WIP get td link)
        DocumentsPage documentsPage = new DocumentsPage(getDriver());
        boolean linkIsPresent = documentsPage.findLinkDocument("Adverse Action Notice.pdf");
        Assert.assertEquals(linkIsPresent, true);

    }

}
