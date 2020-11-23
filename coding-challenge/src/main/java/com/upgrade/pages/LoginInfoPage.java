package com.upgrade.pages;

import com.upgrade.pojos.Borrower;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Log4j
public class LoginInfoPage extends BasePage {

    @FindBy(name = "username")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(linkText = "Terms of Use")
    private WebElement termsOfUse;

    @FindBy(linkText = "ESIGN Act Consent")
    private WebElement eSIGNActConsent;

    @FindBy(linkText = "Credit Profile Authorization")
    private WebElement creditProfileAuthorization;

    @FindBy(linkText = "Privacy Policy")
    private WebElement privacyPolicy;

    @FindBy(css = "[data-auto='submitPersonalInfo']")
    private WebElement checkYourRateBtn;

    @FindBy(name = "agreements")
    private WebElement agreements;

    public LoginInfoPage(WebDriver driver) {
        super(driver);
        waitForWebElements(Arrays.asList(email));
    }

    // Note : Use java generics to return a different page
    public <T extends FunnelBasePage> T enterLoginDetails(Borrower randomPerson){
        type(email, randomPerson.getEmail());
        type(password, randomPerson.getPassword());
        selectTermsOfUse();
        click(checkYourRateBtn);
        waitForPage();
        //We need a logic (url or element) to use java generic and choose the right pageObject
        if (driver.getCurrentUrl().contains("offer-page")){
            log.info("User is redirected to OfferPage");
            return (T) new SelectOfferPage(driver);
        }
        else {
            log.info("User is redirected to AdversePage");
            return (T) new AdversePage(driver);
        }
    }

    public LoginInfoPage selectTermsOfUse() {
        javaScriptClick(agreements);
        return this;
    }
}
