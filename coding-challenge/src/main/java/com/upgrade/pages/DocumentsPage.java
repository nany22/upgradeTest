package com.upgrade.pages;

import lombok.extern.log4j.Log4j;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

@Log4j
public class DocumentsPage extends BasePage {

    @FindBys( {
            @FindBy(css = "table.sc-iXeIkk tbody"),
            @FindBy(tagName = "tr")
    } )
    private List<WebElement> tableRows;

    public DocumentsPage(WebDriver driver){
        super(driver);
    }

    public boolean findLinkDocument(String docName){
        boolean docExist = false;
        boolean linkExist = false;
        for (WebElement row: tableRows) {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            if (tds.get(0).getText().equalsIgnoreCase(docName)) {
                docExist = true;
                log.info("this document exist");
                if (tds.get(2).getAttribute("data-auto").contains("downloadDocument")) {
                    log.info("link for " + docName + " also exist");
                    linkExist = true;
                } else {
                    log.info("document" + docName + "has not a link");
                }
                break;
            }
        }
        if (docExist) System.out.println("document" + docName + "does not exist");
        return linkExist;

    }

}
