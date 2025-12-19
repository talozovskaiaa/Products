package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculateTest {

    Calculate calculate = new Calculate();

    @Test
    void positiveAmountOfMoney() {
        int actual = calculate.calculate(7000, 3000);
        Assertions.assertEquals(8, actual);
    }

    @Test
    void negativeAmountOfMoney() {
        int actual = calculate.calculate(-7000, 50);
        Assertions.assertEquals(0, actual);
    }

    @Test
    void zeroAmountOfMoney() {
        int actual = calculate.calculate(0, 6666);
        Assertions.assertEquals(0, actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testDataForVacation.csv")
    public void vacationTimeParametrize(int expected, int income, int expenses) {
        int actual = calculate.calculate(income, expenses);
        Assertions.assertEquals(expected, actual);
    }

}