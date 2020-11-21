package com.upgrade.pages;

import com.upgrade.pojos.Borrower;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;

@Log4j
public class LandingPage extends BasePage {

    @FindBy(name = "desiredAmount")
    private WebElement loanAmount;

    @FindBy(css = "[data-auto='CheckYourRate']")
    private WebElement checkYourRateBtn;

    @FindBy(css = "[data-auto='dropLoanPurpose']")
    private WebElement dropLoanPurposeBtn;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    /*the 3 fields to complete before getting your approve loan
    * the borrower.get<something> calls to the getters of borrower pojo*/
    public ContactInfoPage enterLoanDetails(Borrower borrower) {
        type(loanAmount, borrower.getDesiredLoanAmount().toString());
        selectDropDownByTextIgnoringCase(dropLoanPurposeBtn, borrower.getLoanPurpose());
        click(checkYourRateBtn);
        return new ContactInfoPage(driver);
    }

    /*Go to the specific url path and wait until loanAmount field is present*/
    public LandingPage gotoLandingPage(String url) {
        String server = String.format("%s/funnel/nonDMFunnel", url);
        log.info("Navigate to - " + server);
        driver.navigate().to(server);
        waitForWebElements(Arrays.asList(loanAmount));
        return this;
    }

 }
