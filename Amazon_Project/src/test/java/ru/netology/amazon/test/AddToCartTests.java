package ru.netology.amazon.test;

import com.microsoft.playwright.Page;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;
import ru.netology.amazon.page.AddToCartPage;
import ru.netology.amazon.page.HomePage;
import ru.netology.amazon.page.MainPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static ru.netology.amazon.page.AddToCartPage.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class AddToCartTests {
    private static MainPage mainPage;

    private HomePage homePage;
    private AddToCartPage addToCartPage;

//    Dotenv dotenv = Dotenv.load();
//    String login = dotenv.get("USER_EMAIL");
//    String password = dotenv.get("USER_PASSWORD");

    @BeforeAll
    static void setupAll() {
        mainPage = new MainPage();
    }

    @BeforeEach
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    public void setup() {
        Page page = mainPage.setUP();
        homePage = new HomePage(page);
        addToCartPage = new AddToCartPage(page);

        assertThat(mainPage.getPage()).hasURL(mainPage.getBaseUrl());
    }

    @AfterEach
    public void tearDown() {
        mainPage.tearDown();
    }

    @Test
    @DisplayName("Переход в Electronics → Camera & Photo")
    void searchAndGoToTheElectronicsSection() {
        addToCartPage.navigateToSection(
                ELECTRONICS_SECTION,
                PHOTO_SUBSECTION,
                RESULT_PHOTO_SUBSECTION
        );
        addToCartPage.addToCart(
        );
    }
}
