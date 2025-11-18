package ru.netology.amazon.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AddToCartPage {

    private final Page page;

    public static final String NAVIGATION_MENU = "#nav-hamburger-menu";
    public static final String ELECTRONICS_SECTION = "a.hmenu-item:has-text(\"Electronics\")";
    public static final String PHOTO_SUBSECTION = "div[data-menu-id=\"6\"] a.hmenu-item:has-text(\"Camera & Photo\")";
    public static final String RESULT_SUBSECTION = "h2.a-size-medium-plus:has-text(\"Results\")";
    public static final String ADD_TO_CART_FIRST_BUTTON = "#a-autoid-1";
    public static final String CART_BUTTON = "#nav-cart";
    public static final String CART_PAGE = "#item-delete-button";
    public static final String SEARCH_FIELD = "#twotabsearchtextbox";
    public static final String SEARCH_BUTTON ="#nav-search-submit-button";
    public static final String ADD_TO_CART_BUTTON_FOR_MACBOOK = "#a-autoid-4-announce";
    public static final String INCREASE_THE_NUMBER = "button[aria-label=\"Increase quantity by one\"]";
    public static final String CONFIRMATION_OF_ITEM_ADDITION = "div.a-stepper-inner-container";
    public static final String SHOPPING_CART = "#sc-active-items-header";

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
    public void addToCart(String locatorByAddToCartButton) {
        page.locator(locatorByAddToCartButton)
                .first()
                .click(new Locator.ClickOptions().setTimeout(10000));

        waitForAddToCartConfirmation();

        page.locator(INCREASE_THE_NUMBER) //добавила
                .first()
                .click(new Locator.ClickOptions().setTimeout(5000));

        page.evaluate("window.scrollTo(0, 0)");
        page.waitForTimeout(1000);

        navigateToCart();
    }

    public void searchItem(String items) {
        page.locator(SEARCH_FIELD).waitFor(new Locator.WaitForOptions().setTimeout(10000));
        page.locator(SEARCH_FIELD).click();
        page.locator(SEARCH_FIELD).fill(items);
        page.locator(SEARCH_BUTTON).click();
        page.locator(RESULT_SUBSECTION).first().waitFor();
    }

    public void waitForAddToCartConfirmation () {
        page.locator(CONFIRMATION_OF_ITEM_ADDITION);
    }

    public void navigateToCart() {
        page.locator(CART_BUTTON).waitFor(new Locator.WaitForOptions().setTimeout(10000));
        page.locator(CART_BUTTON).click();
        page.locator(SHOPPING_CART).waitFor();
    }
}
