package com.upgrade.tests;

import com.github.javafaker.Faker;
import com.upgrade.pojos.Borrower;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AbstractBorrower {

    /** This class added by dany **/

    public Borrower getRandomTestBorrower() {
        Borrower borrower = new Borrower();
        Faker faker = new Faker(new Locale("en-US"));

        borrower.setFirstName(faker.name().firstName());
        borrower.setLastName(faker.name().lastName());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        borrower.setDob(simpleDateFormat.format(faker.date().birthday()));
        borrower.setCity(faker.address().city());
        borrower.setEmail(String.format("coding.%s@upgrade-challenge.com", generateRandomNumberFromRange(15000000, 20000000)));
        borrower.setPassword("System@987");
        borrower.setZipCode(faker.address().zipCode());
        borrower.setStreet(faker.address().streetAddress());
        borrower.setState("CA");
        borrower.setLoanPurpose("Home Improvement");
        borrower.setYearlyIncome(generateRandomNumberFromRange(150000, 200000));
        borrower.setAdditionalIncome(generateRandomNumberFromRange(1000, 10000));
        borrower.setDesiredLoanAmount(generateRandomNumberFromRange(5000, 10000));
        return borrower;
    }

    private BigDecimal generateRandomNumberFromRange(int min, int max) {
        return BigDecimal.valueOf(Math.random() * (max - min + 1) + min).setScale(0, RoundingMode.DOWN);
    }
}
