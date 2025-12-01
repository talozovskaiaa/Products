package ru.netology.amazon.page;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class RemoveAllServicesExample {

    private final Locator navArrowDown;
    private final Locator delete;
    private final Locator deleteApprove;

    public RemoveAllServicesExample(Page page) {
        this.navArrowDown = page.locator(".a-icon.a-icon-small-remove");
        this.delete = page.locator("input[data-action='delete-active']");
        this.deleteApprove = page.locator("your-confirm-delete-button-selector");
    }

    public void removeAll() throws InterruptedException {
        Page page = navArrowDown.page();

        if (navArrowDown.first().isVisible()) {
            navArrowDown.first().click();

            while (delete.count() > 0) {
                delete.first().hover();
                delete.first().click();
//                deleteApprove.click();

                page.waitForSelector("your-nav-arrow-down-selector",
                        new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
            }
        } else if (navArrowDown.first().isHidden()) {
            System.out.println("Все товары удалены");
        }
    }
}