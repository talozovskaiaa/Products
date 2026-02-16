package ru.netology.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ru.netology.data.DataHelper;

public class DashboardPage {

    private final Locator buyButton;
    private final Locator buyOnCreaditButton;
    private final Locator continueButton;
    private final Locator cardNumber;
    private final Locator monthNumber;
    private final Locator yearNumber;
    private final Locator ownerName;
    private final Locator CVCNumber;


    public DashboardPage(Page page) {
        this.buyButton = page.locator("//button[.//span[text()='Купить']]");
        this.buyOnCreaditButton = page.locator("//button[.//span[text()='Купить в кредит']]");
        this.continueButton = page.locator("//button[.//span[text()='Продолжить']]");
        this.cardNumber = page.locator("input.input__control[placeholder='0000 0000 0000 0000']");
        this.monthNumber = page.locator("input.input__control[placeholder='08']");
        this.yearNumber = page.locator("input.input__control[placeholder='22']");
        this.ownerName = page.locator("//span[@class='input__top' and text()='Владелец']/following::input[1]");
        this.CVCNumber = page.locator("input.input__control[placeholder='999']");
    }

    public void enterCardNumber(String value) {
        cardNumber.fill(value);
    }

    public void clickBuyButton() {
        buyButton.click();
    }

    public void continueButton() {
        continueButton.click();
    }

    public void enterMonthNumber(String value) {
        monthNumber.fill(value);
    }

    public void enterYear(String value) {
        yearNumber.fill(value);
    }

    public void enterOwner(String value) {
        ownerName.fill(value);
    }

    public void enterCVCNumber(String value) {
        CVCNumber.fill(value);
    }

    public void fillingOutTheForm(String cardNumber) {
        enterCardNumber(cardNumber);
        enterMonthNumber("01");
        enterYear("22");
        enterOwner("Test");
        enterCVCNumber("999");
    }

    public void fillingOutTheForm1(DataHelper.CardInfo cardInfo) {
        cardNumber.fill(cardInfo.getNumber());
        monthNumber.fill(cardInfo.getMonth());
        yearNumber.fill(cardInfo.getYear());
        ownerName.fill(cardInfo.getHolder());
        CVCNumber.fill(cardInfo.getCvc());
    }

    public void receiveAndClosePush(Locator locator) {
        locator.waitFor();
        locator.click();
    }
}
