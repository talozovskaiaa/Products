package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import lib.ui.WelcomPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase
{
    protected RemoteWebDriver driver;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception
    {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
//        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.skipWelcomePageAndroidApp();
        this.openWikiWebPageForMobileWeb();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown()
    {
        if (driver != null) {
            driver.quit();
        }
    }

//    @Step("Rotate screen to portrait mode")
//    protected void rotateScreenPortrait()
//    {
//        if (driver instanceof AppiumDriver) {
//            AppiumDriver driver = (AppiumDriver) this.driver;
//            driver.rotate(ScreenOrientation.PORTRAIT);
//        } else {
//            System.out.println("Method rotateScreenPortrait() does nothing for platform");
//        }
//    }

//    @Step("Rotate screen to landscape mode")
//    protected void rotateScreenLandscape()
//    {
//        if (driver instanceof AppiumDriver) {
//            AppiumDriver driver = (AppiumDriver) this.driver;
//            driver.rotate(ScreenOrientation.LANDSCAPE);
//    } else {
//            System.out.println("Method rotateScreenPortrait() does nothing for platform");
//        }
//    }

//    @Step("Send mobile app to background (this methode does nothing for Mobile Web)")
//    protected void backgroundApp(int seconds)
//    {
//        if (driver instanceof AppiumDriver) {
//            AppiumDriver driver = (AppiumDriver) this.driver;
//            driver.runAppInBackground(Duration.ofSeconds(seconds));
//    } else {
//            System.out.println("Method rotateScreenPortrait() does nothing for platform");
//        }
//    }

    @Step("Open Wiki URL for Mobile Web (this methode does nothing for Android and IOS")
    protected void openWikiWebPageForMobileWeb()
    {
        if (Platform.getInstance().isMW()) {
            driver.get("https:/en.m.wikipedia.org");
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform");
        }
    }

    @Step("Skip welcome page screen for IOS")
    private void skipWelcomePageForIOSApp()
    {
        if (Platform.getInstance().isIOS()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
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

    private static final String ALLURE_PROPERTY_FILE = "environment.properties";
    private static final String ENVIRONMENT_KEY = "Environment";

    private void createAllurePropertyFile() {
        String path = System.getProperty("allure.result.directory", "target/allure-results");

        if (path == null) {
            throw new RuntimeException("System property 'allure.result.directory' is not set");
        }

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File propertyFile = new File(directory, ALLURE_PROPERTY_FILE);

        try (FileOutputStream fos = new FileOutputStream(propertyFile)) {
            Properties props = new Properties();
            props.setProperty(ENVIRONMENT_KEY, Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://github.com/allure-framework/allure-app/wiki/Environment");
        } catch (Exception e) {
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }

}
