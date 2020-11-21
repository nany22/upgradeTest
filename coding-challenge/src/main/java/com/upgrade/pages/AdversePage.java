package com.upgrade.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j
public class AdversePage extends FunnelBasePage{

    @FindBy(css = "[data-auto='adverse-learn-more-link']")
    private WebElement adverseLink;

    @FindBy(tagName = "body")
    private WebElement declinedMessage;

    public AdversePage(WebDriver driver) {
        super(driver);
    }

    private void adverseLearnMoreLink(){
        click(adverseLink);
    }

    @FindBy(css = "")
    private WebElement loanID;

    public String adverseAction(String url) {
        String loanId = "100018687";
        adverseLearnMoreLink();
        String productPath = String.format("%s/portal/product", url);
        String documentsPath = String.format("%s/%s/documents", productPath, loanId);
        //this is other option
        //String docPath = productPath + loanId + "/document";
        log.info("Making url path " + documentsPath);

        return documentsPath;
    }

    public String getDeclinedMessage() {
        return declinedMessage.getText();
    }

    public String getLoanID(){
        return loanID.getText();
    }
}
