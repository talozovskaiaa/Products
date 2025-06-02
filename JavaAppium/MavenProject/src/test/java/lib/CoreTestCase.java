package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import lib.ui.WelcomPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase extends TestCase
{
    protected RemoteWebDriver driver;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.skipWelcomePageAndroidApp();
        this.openWikiWebPageForMobileWeb();
    }

    @Override
    protected void tearDown() throws Exception
    {
        if (driver != null) {
            driver.quit();
        }
        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform");
        }
    }

    protected void rotateScreenLandscape()
    {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
    } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform");
        }
    }

    protected void backgroundApp(int seconds)
    {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
    } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform");
        }
    }

    protected void openWikiWebPageForMobileWeb()
    {
        if (Platform.getInstance().isMW()) {
            driver.get("https:/en.m.wikipedia.org");
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform");
        }
    }

    private void skipWelcomePageForIOSApp()
    {
        if (Platform.getInstance().isIOS()) {
            RemoteWebDriver driver = (AppiumDriver) this.driver;
            WelcomPageObject WelcomPageObject = new WelcomPageObject(driver);
            WelcomPageObject.clickSkip();
        }
    }

    private void skipWelcomePageAndroidApp()
    {
        if (Platform.getInstance().isAndroid()) {
            RemoteWebDriver driver = (AppiumDriver) this.driver;
            WelcomPageObject WelcomPageObject = new WelcomPageObject(driver);
            WelcomPageObject.initOndoardingInput();
        }
    }

}
