package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            SNACKBAR_ACTION,
            BACK_BUTTON,
            OPEN_NAVIGATION,
            MY_LISTS_LINK;


    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void snackbarAction()
    {
        this.waitForElementAndClick(
                SNACKBAR_ACTION,
                "Cannot press snackbar_action",
                5
        );
    }

    public void backButton()
    {
        this.waitForElementAndClick(
                BACK_BUTTON, //локатор на кнопку назад
                "Cannot Navigate up",
                5
        );
    }

    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button", 5);
        } else {
            System.out.println("Method openNavigation() do nothing for this platform");
        }
    }

    public void clickMyLists()
    {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        }
    }
}
