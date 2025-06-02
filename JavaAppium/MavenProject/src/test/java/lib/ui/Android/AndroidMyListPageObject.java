package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;


public class AndroidMyListPageObject extends MyListPageObject
{
    static {
        XPATHELEMENT = "xpath://*[contains(@text,'Object-oriented programming language')]";
        ARTICLE_BY_TITLE_TMP = "xpath://*[contains(@text,'{TITLE}')]";
    }

    public AndroidMyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
