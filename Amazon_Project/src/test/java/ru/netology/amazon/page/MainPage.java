package ru.netology.amazon.page;

import com.microsoft.playwright.*;
import io.github.cdimascio.dotenv.Dotenv;

import java.nio.file.Paths;

public class MainPage {

    Playwright playwright;
    Browser browser;
    Page page;

    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public static void setupAllureReports() {
        System.setProperty("allure.results.directory", "build/allure-results");
    }

    public static void tearDownAllureReports() {
    }

    public Page setUP() {
        setupAllureReports();

        Dotenv dotenv = Dotenv.load();
        baseUrl = dotenv.get("BASE_URL");

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));

        page = browser.newPage();
        page.navigate(baseUrl);
        page.setDefaultTimeout(5000);

        return page;
    }


    public Page getPage() {
        return page;
    }

    public void tearDown() {

        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
        tearDownAllureReports();
    }

    public Page setUPWithStorageState(String stateFile) {
        setupAllureReports();

        Dotenv dotenv = Dotenv.load();
        baseUrl = dotenv.get("BASE_URL");

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));

        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions().setStorageStatePath(Paths.get(stateFile))
        );

        page = context.newPage();
        page.navigate(baseUrl);
        page.setDefaultTimeout(5000);

        return page;
    }

}