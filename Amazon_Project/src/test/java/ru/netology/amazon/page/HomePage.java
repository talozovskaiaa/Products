package ru.netology.amazon.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {

    private final Page page;

    private final Locator signInButtton;
    private final Locator emailField;
    private final Locator continueButton;
    private final Locator passwordInputField;
    private final Locator signButton;
    private final Locator signOutButton;
    private final Locator pageAfterSignOut;
    private final Locator logoAmazonInHeader;
    private final Locator elementToHover;

    public HomePage(Page page) {
        this.page = page;

        this.signInButtton = page.locator("#nav-link-accountList");
        this.emailField = page.locator("#ap_email_login");
        this.continueButton = page.locator("#continue");
        this.passwordInputField = page.locator("#ap_password");
        this.signButton = page.locator("#auth-signin-button");
        this.signOutButton = page.locator("#nav-item-signout");
        this.pageAfterSignOut = page.locator("#claim-collection-container");
        this.logoAmazonInHeader = page.locator("#nav-logo-sprites");
        this.elementToHover = page.locator("#nav-link-accountList");
    }

    // Метод для авторизации
    public void loginWithValidUser(String login, String password ) {
        signInButtton.click();
        emailField.fill(login);
        continueButton.click();
        passwordInputField.fill(password);
        signButton.click();
        logoAmazonInHeader.isVisible();
    }

    // Метод для разавторизации
    public void sighOut() {
        elementToHover.hover();
        signOutButton.click();
        pageAfterSignOut.waitFor();
    }

    /*
    // Метод для проверки заголовка после авторизации
    public void validationText(String locator, String text) {
        Locator personalAccountHeader = page.locator(locator);
        personalAccountHeader.waitFor();

        String headerText = personalAccountHeader.textContent();
        Assertions.assertTrue(headerText.contains(text),
                "Фактический текст: " + headerText);
    }
     */

}