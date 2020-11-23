package com.upgrade.tests;

import com.github.javafaker.Faker;
import com.upgrade.pojos.Borrower;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class BorrowerDataProvider {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final Faker faker = new Faker(new Locale("en-US"));

    /*Added getTestBorrowerGoodCredit() method to get a borrower user with Good credit*/
    public static Borrower getTestBorrowerGoodCredit() {
        return getTestBorrowerBase()
                .yearlyIncome(generateRandomNumberFromRange(150000, 200000))
                .additionalIncome(generateRandomNumberFromRange(5000, 10000))
                .desiredLoanAmount(generateRandomNumberFromRange(5000, 10000))
                .build();
    }

    /*Added getTestBorrowerBadCredit() method to get a borrower user with Bad credit*/
    public static Borrower getTestBorrowerBadCredit() {
        return getTestBorrowerBase()
                .yearlyIncome(generateRandomNumberFromRange(100, 1000))
                .additionalIncome(generateRandomNumberFromRange(100, 500))
                .desiredLoanAmount(generateRandomNumberFromRange(5000, 10000))
                /*Its not requested being different but just to choose another purpose*/
                .loanPurpose("Business")
                .build();
    }

    private static Borrower.BorrowerBuilder getTestBorrowerBase() {
        return Borrower.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .dob(simpleDateFormat.format(faker.date().birthday()))
                .email(String.format("coding.%s@upgrade-challenge.com", generateRandomNumberFromRange(15000000, 20000000)))
                .password("System@987")
                .city(faker.address().city())
                .zipCode(faker.address().zipCode())
                .street(faker.address().streetAddress())
                .state("CA")
                .loanPurpose("Home Improvement");
    }

    private static BigDecimal generateRandomNumberFromRange(int min, int max) {
        return BigDecimal.valueOf(Math.random() * (max - min + 1) + min).setScale(0, RoundingMode.DOWN);
    }

}
