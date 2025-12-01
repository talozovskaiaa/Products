package ru.netology.amazon.test;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
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

import static ru.netology.amazon.page.ShoppingCartPage.*;

public class AddingTests {

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
        homePage.loginWithValidUser(login, password);

        page.context().storageState(new BrowserContext.StorageStateOptions().setPath(AUTH_FILE_PATH));
        mainPage.tearDown();
    }

    @BeforeEach
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    public void setup() {
        page = mainPage.setUPWithStorageState("auth.json");
        homePage = new HomePage(page);
        shoppingCartPage = new ShoppingCartPage(page);
    }

    @AfterEach
    public void tearDown() {
        try {
            removeAllServicesExample = new RemoveAllServicesExample(page);
            removeAllServicesExample.removeAll();

            int itemsAfterRemoval = shoppingCartPage.getCartItemsCount();
            if (itemsAfterRemoval > 0) {
                System.out.println("Внимание: в корзине осталось " + itemsAfterRemoval + " товаров после removeAll()");
            }

        } catch (Exception e) {
            System.out.println("Ошибка при очистке корзины: " + e.getMessage());
        } finally {
            mainPage.tearDown();
        }
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

    @Test
    @DisplayName("Проверка очистки корзины методом removeAll")
    void testRemoveAllMethod() throws InterruptedException {
        // 1. Добавить товары в корзину
        shoppingCartPage.navigateToSection(
                ELECTRONICS_SECTION,
                PHOTO_SUBSECTION,
                RESULT_SUBSECTION
        );
        shoppingCartPage.addToCart(ADD_TO_CART_FIRST_BUTTON);

        // 2. Проверить, что товары добавлены
        int initialCount = shoppingCartPage.getCartItemsCount();
        Assertions.assertTrue(initialCount > 0, "В корзине должны быть товары");

        // 3. Вызвать метод очистки
        removeAllServicesExample = new RemoveAllServicesExample(page);
        removeAllServicesExample.removeAll();

        // 4. Проверить результат
        int finalCount = shoppingCartPage.getCartItemsCount();
        Assertions.assertEquals(0, finalCount, "Корзина должна быть пустой после removeAll()");
    }
}