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

public class RemovingTests {
    private static MainPage mainPage;
    private HomePage homePage;
    private RemovingTests removingTests;
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
        removingTests = new RemovingTests();
        shoppingCartPage = new ShoppingCartPage(page);
        shoppingCartPage.searchItem("airpods");
        shoppingCartPage.addToCart("a-autoid-1");
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
    @DisplayName("Removing an item from the cart")
    void removingAnItemFromTheCart() {
        shoppingCartPage.deleteAnItemsFromTheCart("input[data-action=\"delete-active\"][type=\"submit\"]", "sc-list-item-removed-msg-text-delete-5e80513f-c676-4c8a-bc75-17b8d0bc6804");
    }
}
