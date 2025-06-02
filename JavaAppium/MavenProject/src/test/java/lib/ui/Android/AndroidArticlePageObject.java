package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "id:org.wikipedia:id/page_contents_container";
        FOOTER_ELEMENT = "xpath://android.view.View[@content-desc='View article in browser']";
        SAVE_BUTTON = "xpath://*[contains(@text,'Save')]";
        ADD_TO_LIST = "xpath://*[contains(@text,'Add to list')]";
        MY_LIST_NAME_INPUT = "xpath://*[contains(@text,'Name of this list')]";
        MY_LIST_OK_BUTTON = "xpath://*[contains(@text,'OK')]";
        MY_EXISTING_LIST = "xpath://*[contains(@text,'{LIST_NAME}')]";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
