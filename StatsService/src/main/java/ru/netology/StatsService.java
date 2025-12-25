package ru.netology;

public class StatsService {

    /**
    Метод, который вычисляет минимальный месяц продаж
     */
    public long minSales(long[] sales) {
        int minMonth = 0;
        int month = 0; // переменная для индекса рассматриваемого месяца в массиве
        for (long sale : sales) {
            // sales[minMonth] - продажи в месяце minMonth
            // sale - продажи в рассматриваемом месяце
            if (sale <= sales[minMonth]) {
                minMonth = month;
            }
            month = month + 1; // следующий рассматриваемый месяц имеет номер на 1 больше
        }
        return minMonth + 1;
    }

    /**
     Метод, который вычисляет максималный месяц продаж
     */
    public long maxSales(long[] sales) {
        int maxMonth = 0;
        int month = 0; // переменная для индекса рассматриваемого месяца в массиве
        for (long sale : sales) {
            // sales[maxMonth] - продажи в месяце maxMonth
            // sale - продажи в рассматриваемом месяце
            if (sale >= sales[maxMonth]) {
                maxMonth = month;
            }
            month = month + 1; // следующий рассматриваемый месяц имеет номер на 1 больше
        }
        return maxMonth + 1;
    }

    /**
     Метод, который вычисляет сумму всех продаж
     */
    public long sumSales(long[] sales) {
        long sum = 0;
        for (long sale : sales) {
            sum += sale;
        }
        return sum;
    }

    /**
     Метод, который вычисляет среднюю сумму продаж в месяц
     */
    public long calculateAverageSales(long[] sales) {
        long averageSales = sumSales(sales) / 12;
        return averageSales;
    }

    /**
     Метод, который вычисляет кол-во месяцев,
     где сумма продажи была ниже среднего
     */
    public long countMonthsBelowAverage(long[] sales) {
        double average = calculateAverageSales(sales);
        int count = 0;
        for (long sale : sales) {
            if (sale < average) {
                count++;
            }
        }
        return count;
    }

    /**
     Метод, который вычисляет кол-во месяцев,
     где сумма продажи была выше среднего
     */
    public long countMonthsAboveAverage(long[] sales) {
        double average = calculateAverageSales(sales);
        int count = 0;
        for (long sale : sales) {
            if (sale > average) {
                count++;
            }
        }
        return count;
    }
}
