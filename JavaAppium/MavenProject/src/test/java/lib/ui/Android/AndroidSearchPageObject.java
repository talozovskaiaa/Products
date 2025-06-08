package lib.ui.Android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_ONBOARDING_ELEMENT = "id:org.wikipedia:id/fragment_onboarding_skip_button";
                SEARCH_INPUT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
                SEARCH_CANCEL_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'{SUBSTRING}')]";
                SEARCH_RESULT_ELEMENT = "id:org.wikipedia:id/page_list_item_title";
                SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[contains(@text,'No results')]";
                SEARCH_RESULT = "id:org.wikipedia:id/navigation_drawer";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
