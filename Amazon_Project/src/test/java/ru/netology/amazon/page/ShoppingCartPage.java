package ru.netology.amazon.page;

import com.microsoft.playwright.Page;

public class ShoppingCartPage {

    private final Page page;

    public final static String FIRST_DELETE_BUTTON = "input[data-action=\"delete-active\"]";
    public final static String TEXT_AFTER_DELETION = "div[data-action='delete']";

    public ShoppingCartPage(Page page) {
        this.page = page;
    }

    public void deleteAnItemsFromTheCart(String locator_by_delete_button, String text_after_deletion) {
        page.click(locator_by_delete_button);
        page.waitForSelector(text_after_deletion);
    }
}
