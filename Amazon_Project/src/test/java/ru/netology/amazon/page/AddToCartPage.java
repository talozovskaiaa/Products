package ru.netology.amazon.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.SplittableRandom;

public class AddToCartPage {

    private final Page page;

    public static final String NAVIGATION_MENU = "#nav-hamburger-menu";
    public static final String ELECTRONICS_SECTION = "a.hmenu-item:has-text(\"Electronics\")";
    public static final String PHOTO_SUBSECTION = "div[data-menu-id=\"6\"] a.hmenu-item:has-text(\"Camera & Photo\")";
    public static final String RESULT_PHOTO_SUBSECTION = "h2.a-size-medium-plus:has-text(\"Results\")";
    public static final String ADD_TO_CART_FIRST_BUTTON = "#a-autoid-1";
    public static final String CART_BUTTON = "#nav-cart";
    public static final String CART_PAGE = "#item-delete-button";
    //public static final String ADD_TO_CART_BUTTON = "#add-to-cart-button";
    public static final String NAV_CART = "#nav-cart";

    public AddToCartPage(Page page) {
        this.page = page;
    }

    // Метод для перехода в любой раздел
    public void navigateToSection(String sectionSelector, String subsectionSelector, String headerSelector) {
        page.locator(NAVIGATION_MENU).click();
        page.locator(sectionSelector).first().click();
        Locator photoItem = page.locator(subsectionSelector).first();
        photoItem.scrollIntoViewIfNeeded();
        photoItem.click(new Locator.ClickOptions()
                .setForce(true)
                .setTimeout(10000));
        page.locator(headerSelector).first().waitFor();
    }

    // Добавление товара в корзину и переход в корзину
    public void addToCart() {
        // Ждём и жмём "Add to Cart"
        page.locator(ADD_TO_CART_FIRST_BUTTON)
                .first()
                .click(new Locator.ClickOptions().setTimeout(10000));

        // Берём иконку корзины
        Locator cart = page.locator(NAV_CART).first();

        // Прокручиваем к ней страницу
        cart.scrollIntoViewIfNeeded();

        // ⚠ Игнорируем actionability-чек 'outside of viewport'
        cart.click(new Locator.ClickOptions()
                .setForce(true)      // ВАЖНО: кликаем даже если Playwright думает, что элемент вне вьюпорта
                .setTimeout(10000));
    }
}
