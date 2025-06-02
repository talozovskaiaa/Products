package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {

    static {
        SNACKBAR_ACTION = "xpath://XCUIElementTypeButton[@name='{LIST_NAME}']"; // не менялось
        BACK_BUTTON = "xpath://XCUIElementTypeButton[contains(@name,'Назад')]"; // не менялось
        MY_LISTS_LINK = "css:a[data-event-name='watchlist']";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
    }

    public MWNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
