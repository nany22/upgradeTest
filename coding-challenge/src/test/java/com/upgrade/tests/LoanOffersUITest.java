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

        //Capture offer details in the Offers page
        /*Create a new instance SelectOfferPage (because values to save are in this page)
        to make easier to save values after login*/
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

        //Capture offer details in the Offers page
        /*Create a new instance SelectOfferPage (because values to save are in this page)
        to make easier to save values after login*/
        SignInPage signInPage = new SignInPage(getDriver());
        SelectOfferPage secondLogin = signInPage
                .gotoSignInPage(url)
                .signIn(borrower);

        //save page values for second login in the `/offer-page`
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
        // Implement Case # 2
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

        //Validate current page (Needs to get loanID WIP)
        //String expectedDocumentsPath = adversePage.adverseAction(url);
        //String actualDocumentsPath = getDriver().getCurrentUrl();
        //Assert.assertEquals(actualDocumentsPath, expectedDocumentsPath);

        //Validate specific PDF link exists
        DocumentsPage documentsPage = new DocumentsPage(getDriver());
        documentsPage.findLinkDocument("Adverse Action Notice.pdf");

    }

}
