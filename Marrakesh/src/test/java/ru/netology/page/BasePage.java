package ru.netology.page;

import com.microsoft.playwright.Locator;

public class BasePage {

    public static void setupAllureReports() {
        System.setProperty("allure.results.directory", "build/allure-results");
    }


}
