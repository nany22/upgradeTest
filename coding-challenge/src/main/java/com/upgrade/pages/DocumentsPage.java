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

    @FindBy(css = "[data-auto='downloadDocument']")
    private WebElement downloadLink;

    public DocumentsPage(WebDriver driver){
        super(driver);
    }

    public void findLinkDocument(String docName){
        //boolean notExist = true;
        //for (WebElement row: tableRows){
        //    List<WebElement> tds = row.findElements(By.tagName("td"));
            //if (tds.get(0).getText().equalsIgnoreCase(docName)){
        //      notExist = false;
         //     log.info("this document exist");
                //if(tds.get(2).getText().contains(downloadLink)){
                //    //tds.get(2).getText();
                //    log.info("link for " + docName +" exist");
          //      }
              //  else log.info("document" + docName + "has not a link");
           // }
       // }
        //if (notExist)  System.out.println("document" + docName + "does not exist");
    }

}
