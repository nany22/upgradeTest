## UpgradeTest
### Overview
This is challenge requested by Upgrade
### Installation
> https://github.com/nany22/upgradeTest.git
### Requirements requested
  - Maven 3.6.0+
  - JDK 11
  - Latest Chrome browser installed
  - Lombok in IDE
      * IntelliJ IDEA 
        - [Lombok](https://projectlombok.org/setup/intellij)  
      * Eclipse 
        - [Lombok](https://projectlombok.org/setup/eclipse) 
        - [TestNG](https://marketplace.eclipse.org/content/testng-eclipse) 
### Test Cases for challenge:
  - Test Case 1: Validate Offer Test (WEB UI)
    * > LoanOffersUITest.java
    * Statement: Using the UI, verify that after filling out the loan application form (with valid inputs), you are seeing loan offers after signing out and back in again.
    * What I did on my own: 
      - Adding assertion for following requirement: Validate that for current page `/offer-page` for default offer (first one on the page) Loan Amount, Monthly Payment, Term, Interest Rate and APR matches with the info captured previously
      - Adding missing attributes like static, final, private.
            
  - Test Case 2: Validate Declined Loan Test (WEB UI)
    * > LoanOffersUITest.java, BorrowerDataProvider.java, AdversePage.java, DocumentsPage.java
    * Statement: Verify that your loan application is rejected when annual income specified is lower than the requested loan amount.
    * What I did on my own: 
      - Adding a new object "BorrowerDataProvider" to make it easier to get a borrower with loan to be accepted and other one to be declined
      - Adding builder to make it easier building complext object
      - Adding java generic to redirect to different page based on accepted or declined loan and make it easy to call methods.
      - Adding AdversePage.java and Documents.java
      - Requested validations:
        - Validate loan is rejected with "We´re sorry, you were not approved." page
        - From the `funnel/adverse-page`Click on "If you would like to learn more about why you were not approved, please click here".
        - Validate that current page is `/portal/product/<loanId>/documents`Validate that a link to "Adverse Action Notice.pdf" exists on the page.
  - Test Case 3: Login to account Test (API)
    * > LoanApiTest.java
    * Statement:
    * What I did on my own: 
    
    Invalid Login to account Test (API)
