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
    public DocumentsPage clickAdverseLearnMoreLink(){
        click(adverseLink);
        waitForPage();
        return new DocumentsPage(driver);
    }

    /* Get the message for when loan was declined */
    public String getDeclinedMessage() {
        waitForPage();
        return declinedMessage.getText();
    }

}
