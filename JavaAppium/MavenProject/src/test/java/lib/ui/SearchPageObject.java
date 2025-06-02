package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_ONBOARDING_ELEMENT,
            SEARCH_INPUT_ELEMENT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RESULT;


    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    public static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initOndoardingInput() {
        this.waitForElementPresent(SEARCH_ONBOARDING_ELEMENT, "Cannot find onboarding skip button");
        this.waitForElementAndClick(SEARCH_ONBOARDING_ELEMENT, "Cannot find and click onboarding skip button", 5);
    }

    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INPUT_ELEMENT, "Cannot find search skip button");
        this.waitForElementAndClick(SEARCH_INPUT_ELEMENT, "Cannot find and click search skip button", 5);
    }

    public void waitForCancelToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search button", 5);
    }

    public void waitForCancelToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search canceled button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT_ELEMENT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 5);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result label by the request ", 15);
    }

    public void waitForResultArticle()
    {
        this.testAssertElementPresent(SEARCH_RESULT);
    }

    public void assertThereIsNotResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_EMPTY_RESULT_ELEMENT, "We supposed not to find any result");
    }
}
