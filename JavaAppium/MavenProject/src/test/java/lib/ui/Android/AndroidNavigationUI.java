package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI
{
    static {
        SNACKBAR_ACTION = "id:org.wikipedia:id/snackbar_action";
        BACK_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        OPEN_NAVIGATION = "";
    }

    public AndroidNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}


