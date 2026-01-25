package ru.netology.test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.BasePage;
import ru.netology.page.BuyATourPage;

public class BuyForCashTest extends BasePage{
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected BuyATourPage buyATourPage;

    @BeforeAll
    static void setUpAll() {}

    @BeforeEach
    void setUp() {
        this.playwright = Playwright.create();
        this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(java.util.Arrays.asList(
                        "--disable-dev-shm-usage",
                        "--no-sandbox"
                )));
        BrowserContext context = browser.newContext();
        this.page = context.newPage();

        page.navigate("http://localhost:9999", new Page.NavigateOptions()
                .setWaitUntil(WaitUntilState.NETWORKIDLE)
                .setTimeout(30000));

        this.buyATourPage = new BuyATourPage(page);
    }

    @AfterEach
    void tearDown() {
        if (page != null) {
            page.close();
        }
    }

    @Test
    void checkTest() {
        buyATourPage.clickBuyButton();
        buyATourPage.enterCardNumber("4444 4444 4444 4441");
        buyATourPage.enterMonthNumber("02");
    }
}
