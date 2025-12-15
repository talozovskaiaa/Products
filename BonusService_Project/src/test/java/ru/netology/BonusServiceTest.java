package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BonusServiceTest {

    BonusService bonusService = new BonusService();

    @Test
    @DisplayName("Расчет бонуса для зарегистрированного пользователя при сумме 16667")
    void calculateBonus_registeredUser_smallAmount_returnsCorrectBonus() {
        long actual = bonusService.calculate(16667, true);
        Assertions.assertEquals(500, actual);
        }

    @Test
    @DisplayName("Расчет бонуса для не зарегистрированного пользователя при сумме 60000")
    void calculateBonus_registeredUser_largeAmount_returnsMaxBonus() {
        long actual = bonusService.calculate(60000, false);
        Assertions.assertEquals(500, actual);
    }

    @Test
    @DisplayName("Расчет бонуса для зарегистрированного пользователя при сумме 0")
    void calculateBonus_registeredUser_zeroAmount_returnsZeroBonus() {
        long actual = bonusService.calculate(0, true);
        Assertions.assertEquals(0, actual);
    }

    @Test
    @DisplayName("Расчет бонуса для не зарегистрированного пользователя при сумме -400")
    void calculateBonus_registeredUser_negativeAmount_returnsNegativeBonus() {
        long actual = bonusService.calculate(-400, false);
        Assertions.assertEquals(-4, actual);
    }

    @Test
    @DisplayName("Расчет бонуса для зарегистрированного пользователя при сумме 16666")
    void calculateBonus_registeredUser_limitAmount_returnsCorrectBonus() {
        long actual = bonusService.calculate(16666, true);
        Assertions.assertEquals(499, actual);
    }

    @Test
    @DisplayName("Расчет бонуса для не зарегистрированного пользователя при сумме 16668")
    void calculateBonus_registeredUser_limitAmount_returnsMaxBonus() {
        long actual = bonusService.calculate(50000, false);
        Assertions.assertEquals(500, actual);
    }

    @Test
    @DisplayName("Расчет бонуса для не зарегистрированного пользователя при сумме 0")
    void calculateBonus_unregisteredUser_zeroAmount_returnsZeroBonus() {
        long actual = bonusService.calculate(0, false);
        Assertions.assertEquals(0, actual);
    }

    @Test
    @DisplayName("Расчет бонуса для зарегистрированного пользователя при сумме 10000")
    void calculateBonus_registeredUser_mediumAmount_returnsCorrectBonus() {
        long actual = bonusService.calculate(10000, true);
        Assertions.assertEquals(300, actual);
    }

    @Test
    @DisplayName("Расчет бонуса для не зарегистрированного пользователя при сумме 30000")
    void calculateBonus_unregisteredUser_mediumAmount_returnsCorrectBonus() {
        long actual = bonusService.calculate(30000, false);
        Assertions.assertEquals(300, actual);
    }

    @Test
    @DisplayName("Расчет бонуса для зарегистрированного пользователя при большой сумме 100000")
    void calculateBonus_registeredUser_veryLargeAmount_returnsMaxBonus() {
        long actual = bonusService.calculate(100000, true);
        Assertions.assertEquals(500, actual);
    }

    @Test
    @DisplayName("Расчет бонуса для не зарегистрированного пользователя при сумме 50001")
    void calculateBonus_unregisteredUser_overLimit_returnsMaxBonus() {
        long actual = bonusService.calculate(50001, false);
        Assertions.assertEquals(500, actual);
    }

    @Test
    @DisplayName("Расчет бонуса для не зарегистрированного пользователя при сумме 49900")
    void calculateBonus_unregisteredUser_overLimit1_returnsMaxBonus() {
        long actual = bonusService.calculate(49900, false);
        Assertions.assertEquals(499, actual);
    }

    @Test
    @DisplayName("Расчет бонуса для зарегистрированного пользователя при отрицательной сумме")
    void calculateBonus_registeredUser_negativeAmount_returnsNegativeBonus1() {
        long actual = bonusService.calculate(-100, true);
        Assertions.assertEquals(-3, actual);
    }

    @Test
    @DisplayName("Расчет бонуса для зарегистрированного пользователя при минимальной положительной сумме 1")
    void calculateBonus_minimumPositiveAmount() {
        long actualRegistered = bonusService.calculate(1, true);
        Assertions.assertEquals(0, actualRegistered);
    }
    }