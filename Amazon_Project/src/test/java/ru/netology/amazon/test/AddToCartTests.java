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

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class AddToCartTests {

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
        shoppingCartPage.searchItem("Macbook Pro");
        shoppingCartPage.addToCart(
                ADD_TO_CART_BUTTON_FOR_MACBOOK
        );
    }
}
