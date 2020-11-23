package com.upgrade.pages;

import com.upgrade.pojos.Borrower;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Arrays;

/*OfferPage extends of FunnelPage because OfferPage is inside of this last one*/
@Log4j
public class SelectOfferPage extends FunnelBasePage {
    /*FindBy declaration represents driver.findElement(By.cssSelector("<selector>"))
    So doing these 3 lines:
    ********************
    @FindBy(css = "[data-auto='getDefaultLoan']")
    private WebElement continueBtn;

    public String <methodName>(){return continueBtn.click();}
    *** is the same than 1those 3 lines ***
    private WebElement continueBtn = "[data-auto='getDefaultLoan']";
    public String <methodName>(){
    return driver.findElement(By.cssSelector("[data-auto='getDefaultLoan']")).click()
    }
    */
    @FindBy(css = "[data-auto='getDefaultLoan']")
    private WebElement continueBtn;

    /*Added UserLoanAmount*/
    @FindBy(css="[data-auto='userLoanAmount']")
    private WebElement loanAmount;

    //$$('[data-auto=defaultMonthlyPayment]')[0].textContent
    @FindBy(css = "[data-auto='defaultMonthlyPayment']")
    private WebElement defaultMonthlyPayment;

    @FindBy(css = "[data-auto='defaultLoanTerm']")
    private WebElement defaultLoanTerm;

    @FindBy(css = "[data-auto='defaultLoanInterestRate']")
    private WebElement defaultLoanInterestRate;

    @FindBy(css = "[data-auto='defaultMoreInfoAPR']")
    private WebElement loanAPR;


    public SelectOfferPage(WebDriver driver) {
        super(driver);
        waitForWebElements(Arrays.asList(continueBtn));
    }

    public String getLoanAmount() {
        return loanAmount.getText();
    }
    public String getMonthlyPayment() {
        return defaultMonthlyPayment.getText();
    }

    public String getLoanTerm() {
        return defaultLoanTerm.getText();
    }

    public String getLoanInterestRate() {
        return defaultLoanInterestRate.getText();
    }

    public String getLoanAPR() {
        return loanAPR.getText();
    }



}
