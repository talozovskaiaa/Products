package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;

import org.junit.Test;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomPageObject;

public class GetStartedTests extends CoreTestCase {

    @Test
    @Feature(value = "Welcome page")
    @DisplayName("Skip the Welcome page")
//    @Description("We skip the Welcome page")
    @Step("Starting test testPassThroughWelcome")
    @Severity(value = SeverityLevel.NORMAL)
    public void testPassThroughWelcome()
    {
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())) {
            return;
        }

        WelcomPageObject WelcomePage = new WelcomPageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddOrEditPreferredLangText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearnMoreAboutDataCollectedText();
        WelcomePage.clickGetStartedButton();

    }
}
