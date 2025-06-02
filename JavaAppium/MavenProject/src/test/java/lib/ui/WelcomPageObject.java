package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomPageObject extends MainPageObject
{

    public static final String
            STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[contains(@name, 'Узнать подробнее о Википедии')]",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "xpath://XCUIElementTypeStaticText[contains(@name, 'Добавить или изменить предпочтительные языки')]",
            STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "xpath://XCUIElementTypeStaticText[contains(@name, 'Новые способы изучения')]",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "xpath://XCUIElementTypeStaticText[contains(@name, 'Узнать подробнее о сборе данных')]",
            NEXT_LINK = "xpath://XCUIElementTypeStaticText[contains(@name, 'Далее')]",
            GET_STARTED_BUTTON = "xpath://XCUIElementTypeStaticText[contains(@name, 'Начать')]",
            SKIP = "xpath://XCUIElementTypeStaticText[contains(@name, 'Пропустить')]",
            SEARCH_ONBOARDING_ELEMENT = "id:org.wikipedia:id/fragment_onboarding_skip_button";

    public WelcomPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Узнать подробнее о Википедии' link", 15);
    }

    public void waitForAddOrEditPreferredLangText()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find 'Добавить или изменить предпочтительные языки' link", 15);
    }

    public void waitForNewWayExploreText()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK, "Cannot find 'Новые способы изучения' link", 15);
    }

    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find 'Узнать подробнее о сборе данных' link", 15);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find 'Далее' button", 15);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find 'Начать' button", 15);
    }

    public void clickSkip()
    {
        this.waitForElementAndClick(SKIP, "Cannot find and click skip button", 5);
    }

    public void initOndoardingInput() {
        this.waitForElementPresent(SEARCH_ONBOARDING_ELEMENT, "Cannot find onboarding skip button");
        this.waitForElementAndClick(SEARCH_ONBOARDING_ELEMENT, "Cannot find and click onboarding skip button", 5);
    }
}
