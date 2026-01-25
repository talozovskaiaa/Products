package ru.netology.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BuyATourPage {

    private final Locator buyButton;
    private final Locator buyOnCreaditButton;
    private final Locator continueButton;
    private final Locator cardNumber;
    private final Locator monthNumber;
    private final Locator yearNumber;
    private final Locator ownerName;
    private final Locator CVCNumber;

    public BuyATourPage(Page page) {
        this.buyButton = page.locator("//button[.//span[text()='Купить']]");
        this.buyOnCreaditButton = page.locator("//button[.//span[text()='Купить в кредит']]");
        this.continueButton = page.locator("//button[.//span[text()='Продолжить']]");
        this.cardNumber = page.locator("input.input__control[placeholder='0000 0000 0000 0000']");
        this.monthNumber = page.locator("input.input__control[placeholder='08']");
        this.yearNumber = page.locator("input.input__control[placeholder='22']");
        this.ownerName = page.locator("//span[@class='input__top' and text()='Владелец']");
        this.CVCNumber = page.locator("input.input__control[placeholder='999']");
    }

    public void enterCardNumber(String value) {
        cardNumber.fill(value);
    }

    public void clickBuyButton() {
        buyButton.click();
    }

    public void enterMonthNumber(String value) {
        monthNumber.fill(value);
    }

    public void receiveAndClosePush(Locator locator) {
        locator.waitFor();
        locator.click();
    }
}
