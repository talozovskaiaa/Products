package ru.netology.amazon.test;

import com.microsoft.playwright.Page;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;
import ru.netology.amazon.page.HomePage;
import ru.netology.amazon.page.MainPage;
import ru.netology.amazon.page.ShoppingCartPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static ru.netology.amazon.page.ShoppingCartPage.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class AddToCartTests {
    private static MainPage mainPage;

    private HomePage homePage;
    private ShoppingCartPage shoppingCartPage;

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
        shoppingCartPage = new ShoppingCartPage(page);

        assertThat(mainPage.getPage()).hasURL(mainPage.getBaseUrl());
    }

    @AfterEach
    public void tearDown() {
        mainPage.tearDown();
    }

    @Test
    @DisplayName("Переход в Electronics → Camera & Photo")
    void searchAndGoToTheElectronicsSection() {
        shoppingCartPage.navigateToSection(
                ELECTRONICS_SECTION,
                PHOTO_SUBSECTION,
                RESULT_SUBSECTION
        );
        shoppingCartPage.addToCart(
                ADD_TO_CART_FIRST_BUTTON
        );
    }

    @Test
    @DisplayName("Поиск 'Macbook Pro' и добавление в корзину")
    void searchWithSearchField() {
        homePage.loginWithValidUser(login, password);
        shoppingCartPage.searchItem("Macbook Pro");
        shoppingCartPage.addToCart(
                ADD_TO_CART_BUTTON_FOR_MACBOOK
        );
    }
}
