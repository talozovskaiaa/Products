package ru.netology.data;

public final class DataHelper {
    private DataHelper() {}

    public static class CardInfo {
        private final String number;
        private final String month;
        private final String year;
        private final String holder;
        private final String cvc;

        public CardInfo(String number, String month, String year, String holder, String cvc) {
            this.number = number;
            this.month = month;
            this.year = year;
            this.holder = holder;
            this.cvc = cvc;
        }

        public String getNumber() { return number; }
        public String getMonth() { return month; }
        public String getYear() { return year; }
        public String getHolder() { return holder; }
        public String getCvc() { return cvc; }
    }

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getValidMonth() {
        return "12";
    }

    public static String getValidYear() {
        return "26";
    }

    public static String getValidHolder() {
        return "IVAN IVANOV";
    }

    public static String getValidCVC() {
        return "123";
    }
}
