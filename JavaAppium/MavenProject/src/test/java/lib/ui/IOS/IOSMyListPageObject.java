package lib.ui.IOS;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListPageObject extends MyListPageObject
{
    static
    {
        XPATHELEMENT = "xpath://XCUIElementTypeStaticText[contains(@name,'Diamond Rush')]";
        ARTICLE_BY_TITLE_TMP = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
    }

    public IOSMyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
