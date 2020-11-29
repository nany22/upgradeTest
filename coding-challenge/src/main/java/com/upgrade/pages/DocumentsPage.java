package com.upgrade.pages;

import lombok.extern.log4j.Log4j;
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
    /* Find document in the documents page and check it contains a link
    * @parameter docName is the document for searching */
        boolean docExist = false;
        boolean linkExist = false;
        for (WebElement row: tableRows) {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            if (tds.get(0).getText().equalsIgnoreCase(docName)) {
                docExist = true;
                log.info("document "+docName+ " in the /documents page exists");
                if (tds.get(2).findElement(By.tagName("a")).getAttribute("data-auto").equalsIgnoreCase("downloadDocument"))
                {
                    log.info("link for " + docName + " in the /documents page also exist");
                    linkExist = true;
                } else {
                    log.info("document" + docName + "has not a link");
                }
                break;
            }
        }
        if (!docExist) System.out.println("document" + docName + " does not exist");
        return linkExist;
    }

}
