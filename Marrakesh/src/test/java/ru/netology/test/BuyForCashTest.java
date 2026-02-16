package ru.netology.test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.BasePage;
import ru.netology.page.DashboardPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;


public class BuyForCashTest extends BasePage{
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected DashboardPage buyATourPage;

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

        page.navigate("http://localhost:8080", new Page.NavigateOptions()
                .setWaitUntil(WaitUntilState.NETWORKIDLE)
                .setTimeout(30000));

        this.buyATourPage = new DashboardPage(page);
    }

    @AfterEach
    void tearDown() {
        if (page != null) {
            page.close();
        }
    }

    @Test
    @DisplayName("Покупка APPROVED карта")
    void theCardPaymentMustBeNotApproved() {
        var cardinfo = new DataHelper.CardInfo(
                getApprovedCardNumber(),
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCVC()
        );

        buyATourPage.clickBuyButton();
        buyATourPage.fillingOutTheForm1(cardinfo);
        buyATourPage.continueButton();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Покупка DECLINED карта")
    void theCardPaymentMustBeApproved() {
        var cardinfo = new DataHelper.CardInfo(
                getDeclinedCardNumber(),
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCVC()
        );

        buyATourPage.clickBuyButton();
        buyATourPage.fillingOutTheForm1(cardinfo);
        buyATourPage.continueButton();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }
}
