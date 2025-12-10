package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void addTest() {
        int actual = Calculator.add(1, 2);
        Assertions.assertEquals(3, actual);
    }

    @Test
    void subtractTest() {
        int actual = Calculator.subtract(3, 3);
        Assertions.assertEquals(0, actual);
    }

    @Test
    void multiplyTest() {
        int actual = Calculator.multiply(3, 1);
        Assertions.assertEquals(3, actual);
    }

    @Test
    void divideTest() {
        int actual = Calculator.divide(6, 2);
        Assertions.assertEquals(3, actual);
    }
}