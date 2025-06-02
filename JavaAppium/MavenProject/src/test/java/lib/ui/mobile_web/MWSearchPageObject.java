package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INPUT_ELEMENT = "css:#searchInput";
        SEARCH_CANCEL_BUTTON = "css:.mf-icon.mf-icon-close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "css://div[contains(@class,'wikipedia-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "css:li.page-summary[data-id='1588']";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
    }

    public MWSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
