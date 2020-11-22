package com.upgrade.tests;

import com.github.javafaker.Faker;
import com.upgrade.pojos.Borrower;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class BorrowerDataProvider {
    //Check: IS OK IT DOES NOT EXTENDS NOTHING??????????

    /*those 2 lines were set at the top of class instead of inside of getTestBorrowerBase method,
    * to make sure this object is not created any time I call to the method, due to it is static
    *  and it is not setting anything*/
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final Faker faker = new Faker(new Locale("en-US"));


    /*Added getTestBorrowerGoodCredit() method to get a borrower user with Good credit
     * it will be loaded at memory at compilation time, so its "static"
     * it finishes building for borrower user coming from getTestBorrowerBase
     * it will be public for being able to access from other classes*/
    public static Borrower getTestBorrowerGoodCredit() {
        return getTestBorrowerBase()
                .yearlyIncome(generateRandomNumberFromRange(150000, 200000))
                .additionalIncome(generateRandomNumberFromRange(5000, 10000))
                .desiredLoanAmount(generateRandomNumberFromRange(5000, 10000))
                .build();
    }

    /*Added getTestBorrowerBadCredit() method to get a borrower user with Bad credit
    * it will be loaded at memory at compilation time, so its "static"
    * it finishes building for borrower user coming from getTestBorrowerBase
    * it will be public for being able to access from other classes*/
    public static Borrower getTestBorrowerBadCredit() {
        return getTestBorrowerBase()
                .yearlyIncome(generateRandomNumberFromRange(100, 1000))
                .additionalIncome(generateRandomNumberFromRange(100, 500))
                .desiredLoanAmount(generateRandomNumberFromRange(5000, 10000))
                /*Its not requested being different but just to show I know there are several dropdown options*/
                .loanPurpose("Business")
                .build();
    }

    /*I added "static" because this method will be loaded at memory at compilation time
    /*was used the builder method as alternative way to construct complex objects, making the borrower user base
    and then continue creating the immutable object in each method needed.
    Instead of set as follow "borrower.setFirstName(faker.name().firstName());", with
    builder we can set each field as ".firstName(faker.name().firstName());"
    Borrower.builder() is like a builder of everything inside Borrower pojo*/
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

    /*I added "static" because this method will be loaded at memory at compilation time*/
    private static BigDecimal generateRandomNumberFromRange(int min, int max) {
        return BigDecimal.valueOf(Math.random() * (max - min + 1) + min).setScale(0, RoundingMode.DOWN);
    }

}
