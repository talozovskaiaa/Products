package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUI {

    static {
        SNACKBAR_ACTION = "xpath://XCUIElementTypeButton[@name='{LIST_NAME}']";
        BACK_BUTTON = "xpath://XCUIElementTypeButton[contains(@name,'Назад')]";
        OPEN_NAVIGATION = "";
    }

    public IOSNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
