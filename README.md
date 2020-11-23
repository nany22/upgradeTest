:point_right: ## UpgradeTest:point_left:
### Overview
This is challenge requested by Upgrade
### Installation
> git clone https://github.com/nany22/upgradeTest.git
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
  - *Test Case 1: Validate Offer Test (WEB UI)*
    * > LoanOffersUITest.java
    * Statement: Using the UI, verify that after filling out the loan application form (with valid inputs), you are seeing loan offers after signing out and back in again.
    * What I did on my own: 
      - [x] Adding assertion for following requirement: Validate that for current page `/offer-page` for default offer (first one on the page) Loan Amount, Monthly Payment, Term, Interest Rate and APR matches with the info captured previously
      - [x] Adding missing attributes like static, final, private.
            
  - *Test Case 2: Validate Declined Loan Test (WEB UI)*
    * > LoanOffersUITest.java, BorrowerDataProvider.java, AdversePage.java, DocumentsPage.java
    * Statement: Verify that your loan application is rejected when annual income specified is lower than the requested loan amount.
    * What I did on my own: 
      - [x] Adding a new object "BorrowerDataProvider" to make it easier to get a borrower with loan to be accepted and other one to be declined
      - [x] Adding builder to make it easier building complext object
      - [x] Adding java generic to redirect to different page based on accepted or declined loan and make it easy to call methods.
      - [x] Adding AdversePage.java and Documents.java
      - [] Requested validations:
        - [x] Validate loan is rejected with "We´re sorry, you were not approved." page
        - [x] From the `funnel/adverse-page`Click on "If you would like to learn more about why you were not approved, please click here".
        - [x] Validate that current page is `/portal/product/<loanId>/documents`Validate that a link to "Adverse Action Notice.pdf" exists on the page.
  - *Test Case 3: Login to account Test (API)*
    * > LoanApiTest.java, LoanAccountResponse.java, LoanInReviewResponse.java, LoanResponse.java
    * Statement: Upgrade exposes a login API for registered borrowers, that should provide a 200 (OK) response if valid credentials are specified in the request. And a 401 response if the specified credentials are invalid. 
The API endpoint is available at: https://credapi.credify.tech/api/brportorch/v2/login
In your automated tests - validate atleast the following, and feel free to include any other validations that you deem necessary:
    1. Validate that for correct credentials provided in the payload above, the API response code is a 200 (OK)
    2. For the above use case, parse each json value in the response payload individually and then validate the productType attribute has value PERSONAL_LOAN
    3. Validate that in the initial POST request, if a different username is provided (that doesn't exist in our system) - the API response is a 401 (UnAuthorized)
    * What I did on my own: 
      - [x] Adding new LoanResponse.java to load the json data got after request
      - [x] Adding LoanAccountResponse.java and LoanInReviewResponse.java for loading lists data from UserResponse
      - [] For requested Validation:
          - [x] 1. was already done
          - 2. [x] Adding assert for product type data from LoanInReview list and other extra fields from LoanAccountResponse and firstname from UserResponse.
          - 3. [x] Writing a new test case 4: **Invalid Logging to account test (API)** for 401 cose status validation after request is sent.
          
          
          
       :smiley:
   
