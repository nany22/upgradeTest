package com.upgrade.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AdversePage extends FunnelBasePage{

    @FindBy(css = "[data-auto='adverse-learn-more-link']")
    private WebElement adverseLink;

    @FindBy(tagName = "body")
    private WebElement declinedMessage;

    public AdversePage(WebDriver driver) {
        super(driver);
    }

    /* Click on "learn more" link */
    public void adverseLearnMoreLink(){
        click(adverseLink);
        By by = By.cssSelector("[data-auto='layoutTitle']");
        waitForElementToBeDisplayed(by, 10, 1);
    }

    /* Get the message for when loan was declined */
    public String getDeclinedMessage() {
        return declinedMessage.getText();
    }

}
