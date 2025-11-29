package ru.netology.amazon.test;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;
import ru.netology.amazon.page.HomePage;
import ru.netology.amazon.page.MainPage;
import ru.netology.amazon.page.ShoppingCartPage;

import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.netology.amazon.page.ShoppingCartPage.*;

public class ShoppingCartTests {

    private static MainPage mainPage;
    private HomePage homePage;
    private ShoppingCartPage shoppingCartPage;

    private static Dotenv dotenv = Dotenv.load();
    private static String login = dotenv.get("USER_EMAIL");
    private static String password = dotenv.get("USER_PASSWORD");
    private static final Path AUTH_FILE_PATH = Paths.get("auth.json");

    @BeforeAll
    static void setupAll() {
        mainPage = new MainPage();

        Page page = mainPage.setUP();
        HomePage homePage = new HomePage(page);
        homePage.loginWithValidUser(login, password);

        page.context().storageState(new BrowserContext.StorageStateOptions().setPath(AUTH_FILE_PATH));
        mainPage.tearDown();
    }

    @BeforeEach
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    public void setup() {
        Page page = mainPage.setUPWithStorageState("auth.json");
        homePage = new HomePage(page);
        shoppingCartPage = new ShoppingCartPage(page);
    }

    @AfterEach
    public void tearDown() {
        mainPage.tearDown();
    }

    @AfterAll
    static void tearDownAll() {
        try {
            java.nio.file.Files.deleteIfExists(AUTH_FILE_PATH);
        } catch (Exception e) {
            System.out.println("Не удалось удалить файл auth.json: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Удаление 'Macbook Pro' из корзины")
    void removingAnItemFromTheCart() {
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
