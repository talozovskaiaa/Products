package ru.netology.amazon.test;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import ru.netology.amazon.page.HomePage;
import ru.netology.amazon.page.MainPage;

public class MockTest {

    private static MainPage mainPage;
    private HomePage homePage;
    private Page page;

    @BeforeAll
    static void setupAll() {
        mainPage = new MainPage();
    }

    @BeforeEach
    void setUp() {
        page = mainPage.setUP();
        homePage = new HomePage(page);
    }

    @Test
    void mockProductSearchApi() {
        page.route("**/api/products*", route -> {
            String mockResponse = """
                {
                    "products": [
                        {
                            "id": "B08N5WRWNW",
                            "title": "Mock MacBook Pro",
                            "price": 1299.99,
                            "rating": 4.7,
                            "reviews": 2450,
                            "inStock": true
                        },
                        {
                            "id": "B08N5LNQCX",
                            "title": "Mock iPhone 13",
                            "price": 899.99,
                            "rating": 4.8,
                            "reviews": 1890,
                            "inStock": true
                        }
                    ],
                    "totalResults": 2
                }
                """;

            route.fulfill(new Route.FulfillOptions()
                    .setStatus(200)
                    .setContentType("application/json")
                    .setBody(mockResponse));
        });
        Response response = page.navigate("https://www.amazon.com");
        Assertions.assertEquals(200, response.status(),
                "Товары успешно найдены");
    }

    @Test
    void mockAddToCartApi() {
        page.route("**/api/cart/add*", route -> {
            String mockResponse = """
            {
                "success": true,
                "cartItemCount": 3,
                "message": "Item added to cart successfully",
                "itemId": "B08N5WRWNW",
                "price": 1299.99
            }
            """;

            route.fulfill(new Route.FulfillOptions()
                    .setStatus(200)
                    .setContentType("application/json")
                    .setBody(mockResponse));
        });
        Response response = page.navigate("https://www.amazon.com/gp/cart/view.html");
        Assertions.assertEquals(200, response.status(),
                "Страница должна загрузиться успешно");
    }

    @Test
    void mockLoginApiSuccess() {
        page.route("**/api/auth/login*", route -> {
            String mockResponse = """
            {
                "success": true,
                "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                "user": {
                    "id": "user123",
                    "email": "test@example.com",
                    "name": "Test User"
                },
                "expiresIn": 3600
            }
            """;

            route.fulfill(new Route.FulfillOptions()
                    .setStatus(200)
                    .setContentType("application/json")
                    .setBody(mockResponse));
        });

        Response response = page.navigate("https://www.amazon.com/ap/signin");
        Assertions.assertEquals(200, response.status(),
                "Пользователь авторизован успешно");
    }

    @AfterEach
    public void tearDown() {
        mainPage.tearDown();
    }
}