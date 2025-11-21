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
import static ru.netology.amazon.page.ShoppingCartPage.ADD_TO_CART_BUTTON_FOR_MACBOOK;
import static ru.netology.amazon.page.ShoppingCartPage.FIRST_DELETE_BUTTON;
import static ru.netology.amazon.page.ShoppingCartPage.TEXT_AFTER_DELETION;

public class ShoppingCartTests {

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
    @DisplayName("Удаление 'Macbook Pro' из корзины")
    void removingAnItemFromTheCart() {
        homePage.loginWithValidUser(
                login,
                password
        );
        shoppingCartPage.searchItem("Macbook Pro");
        shoppingCartPage.addToCart(
                ADD_TO_CART_BUTTON_FOR_MACBOOK
        );
        shoppingCartPage.deleteAnItemsFromTheCart(
                FIRST_DELETE_BUTTON,
                TEXT_AFTER_DELETION
        );
    }

    @Test
    @DisplayName("Уменьшение товара на 1 и оформление заказа")
    void proceedToCheckoutAction() {
        homePage.loginWithValidUser(
                login,
                password
        );
        shoppingCartPage.searchItem("Macbook Pro");
        shoppingCartPage.addToCart(
                ADD_TO_CART_BUTTON_FOR_MACBOOK
        );
        shoppingCartPage.decreaseTheNumberInCart();
        shoppingCartPage.proceedToCheckout();
        shoppingCartPage.addANewAddress(
                "+1(800)469-92-69",
                "Street 757 Or Rndm Address , New York, NY 10081",
                "12"
        );
    }
}
