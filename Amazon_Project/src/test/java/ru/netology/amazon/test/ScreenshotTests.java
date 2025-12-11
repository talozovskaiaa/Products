package ru.netology.amazon.test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;
import ru.netology.amazon.page.HomePage;
import ru.netology.amazon.page.MainPage;
import ru.netology.amazon.page.RemoveAllServicesExample;
import ru.netology.amazon.page.ShoppingCartPage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScreenshotTests {

    private static MainPage mainPage;
    private HomePage homePage;
    private ShoppingCartPage shoppingCartPage;
    private RemoveAllServicesExample removeAllServicesExample;
    private Page page;

    private static final Dotenv dotenv = Dotenv.load();
    private static final String login = dotenv.get("USER_EMAIL");
    private static final String password = dotenv.get("USER_PASSWORD");
    private static final Path AUTH_FILE_PATH = Paths.get("auth.json");

    @BeforeAll
    static void setupAll() {
        mainPage = new MainPage();

        Page page = mainPage.setUP();
        HomePage homePage = new HomePage(page);
    }

    @BeforeEach
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    public void setup() {
        homePage = new HomePage(page);
    }

    @AfterEach
    public void tearDown() {
            mainPage.tearDown();
    }


    @Test
    public void takeFullPageScreenshot() {
        final String websiteLink = "https://www.amazon.com/";
        final Page page = mainPage.getPage();
        page.navigate(websiteLink);

        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd_hh:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("./screenshots/screenshot_" + dateFormat.format(new Date()) + ".png")).setFullPage(true));

        assertEquals(page.url(), websiteLink);
    }

    @Test
    void testPlaywrightInstallation() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            System.out.println("Playwright успешно установлен!");
            browser.close();
        }
    }

}
