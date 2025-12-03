package ru.netology.amazon.test;

import com.microsoft.playwright.Page;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;
import ru.netology.amazon.page.HomePage;
import ru.netology.amazon.page.MainPage;
import io.qameta.allure.*;
import ru.netology.amazon.utils.ThreadManager;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class SignInTests {

    private static MainPage mainPage;

    HomePage homePage;

    Dotenv dotenv = Dotenv.load();
    String login = dotenv.get("USER_EMAIL");
    String password = dotenv.get("USER_PASSWORD");

    @BeforeAll
    static void setupAll() {
        mainPage = new MainPage();
    }


    @BeforeEach
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    public void setup() {

        Page page = mainPage.setUP();
        homePage = new HomePage(page);
        assertThat(mainPage.getPage()).hasURL(mainPage.getBaseUrl());
    }

    @AfterEach
    public void tearDown() {
        mainPage.tearDown();
    }

    @Test
    @DisplayName("Successful authorization")
    @Severity(SeverityLevel.BLOCKER)
    void sighIn() {
        ThreadManager.runAmazonTests();
        homePage.loginWithValidUser(login, password);
    }

    @Test
    @DisplayName("Successful unauthorization")
    @Severity(SeverityLevel.BLOCKER)
    void sighOut() {
        ThreadManager.runAmazonTests();
        homePage.loginWithValidUser(login, password);
        homePage.sighOut();
    }
}