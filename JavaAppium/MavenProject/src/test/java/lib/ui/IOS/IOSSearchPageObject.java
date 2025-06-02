package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_ONBOARDING_ELEMENT = "id:org.wikipedia:id/fragment_onboarding_skip_button"; // на иос нет онбординга
        SEARCH_INPUT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Поиск по Википедии']";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Отменить']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='Ничего не найдено']";
        SEARCH_RESULT = "id:org.wikipedia:id/navigation_drawer"; // Не менялась, проверить далее по тестам
    }

    public IOSSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
