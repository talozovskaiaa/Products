package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatsServiceTests {

    StatsService service = new StatsService();
    long[] sales = {8, 15, 13, 15, 17, 20, 19, 20, 7, 14, 14, 18};

    @Test
    @DisplayName("Номер месяца, в котором был минимум продаж")
    void minSalesTest() {
        long actual = service.minSales(sales);
        Assertions.assertEquals(9, actual);
    }

    @Test
    @DisplayName("Номер месяца, в котором был пик продаж")
    void maxSalesTest() {
        long actual = service.maxSales(sales);
        Assertions.assertEquals(8, actual);
    }

    @Test
    @DisplayName("Сумму всех продаж")
    void sumSalesTest() {
        long actual = service.sumSales(sales);
        Assertions.assertEquals(180, actual);
    }

    @Test
    @DisplayName("Среднюю сумму продаж в месяц")
    void calculateAverageSalesTest() {
        long actual = service.calculateAverageSales(sales);
        Assertions.assertEquals(15, actual);
    }

    @Test
    @DisplayName("Количество месяцев, в которых продажи были ниже среднего")
    void countMonthsBelowAverageTest() {
        long actual = service.countMonthsBelowAverage(sales);
        Assertions.assertEquals(5, actual);
    }

    @Test
    @DisplayName("Количество месяцев, в которых продажи были выше среднего")
    void countMonthsAboveAverageTest() {
        long actual = service.countMonthsAboveAverage(sales);
        Assertions.assertEquals(5, actual);
    }
}