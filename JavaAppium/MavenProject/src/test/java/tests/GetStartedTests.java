package tests;

import org.junit.Test;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomPageObject;

public class GetStartedTests extends CoreTestCase {

    @Test
    public void testPassThroughWelcome()
    {
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())) {
            return;
        }

        WelcomPageObject WelcomPage = new WelcomPageObject(driver);

        WelcomPage.waitForLearnMoreLink();
        WelcomPage.clickNextButton();

        WelcomPage.waitForNewWayExploreText();
        WelcomPage.clickNextButton();

        WelcomPage.waitForAddOrEditPreferredLangText();
        WelcomPage.clickNextButton();

        WelcomPage.waitForLearnMoreAboutDataCollectedText();
        WelcomPage.clickGetStartedButton();

    }
}
