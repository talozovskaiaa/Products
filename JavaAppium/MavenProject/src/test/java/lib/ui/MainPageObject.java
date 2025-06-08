package lib.ui;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lib.Platform;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutIntSeconds) {
        By by = this.getLocatorString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutIntSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSecond);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSecond);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeOutInSeconds) {
        By by = this.getLocatorString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public void scrollWebPageUp() {
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform");
        }
    }

    public void scrollWebPageTitleElementNotVisible(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;

        WebElement element = this.waitForElementPresent(locator, error_message);

        while (!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSecond);
        element.clear();
        return element;
    }

    // Тема 3, ДЗ N 1
    public WebElement assertElementHasText(String locator, String value, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message);
        element.sendKeys(value);
        return element;
    }

    // Пример метода swipUp

    public void swipeUp1(int timeOfSwipe) {
        try {
            Dimension size = driver.manage().window().getSize();
            if (size == null || size.width <= 0 || size.height <= 0) {
                throw new RuntimeException("Unable to get screen size");
            }

            System.out.println("Screen size: " + size);

            if (Platform.getInstance().isAndroid()) {
                // Свайп вверх для Android
                int x = size.width / 2;
                int start_y = (int) (size.height * 0.8);
                int end_y = (int) (size.height * 0.2);

                PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
                Sequence swipe = new Sequence(finger, 1)
                        .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, start_y))
                        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                        .addAction(finger.createPointerMove(Duration.ofMillis(timeOfSwipe), PointerInput.Origin.viewport(), x, end_y))
                        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

                driver.perform(Collections.singletonList(swipe));
            } else if (Platform.getInstance().isIOS()) {

                int left_x = 20;
                int right_x = 131;
                int middle_y = 118;

                PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

                Sequence swipe = new Sequence(finger, 1)
                        .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), left_x, middle_y)) // Начало свайпа
                        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // Нажатие
                        .addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), right_x, middle_y)) // Движение вправо
                        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Отпускание

                driver.perform(Collections.singletonList(swipe));
            } else {
                throw new RuntimeException("Unsupported platform");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to perform swipe action", e);
        }
    }

    public void swipeUpQuick() {
        swipeUp1(200);
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {
        By by = this.getLocatorString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes)
    {
        int already_swiped = 0;

        while (!this.isElementLocatedOnTheScreen(locator))
        {
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator)
    {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 5).getLocation().getY();
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Objects js_result = (Objects) JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public void clickElementInTheRightUpperCorner(String locator, String error_message) {

        WebElement element = this.waitForElementPresent(locator + "/..", error_message);

        int left_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();

        int right_x = left_x + width; // Правая граница элемента
        int middle_y = upper_y + (height / 2); // Середина по вертикали


        int point_to_click_x = right_x - 3;
        int point_to_click_y = middle_y;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), point_to_click_x, point_to_click_y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));
    }

    public void swipeElementToLeft(String locator, String error_message) {
        try {
            // Находим элемент
            WebElement element = waitForElementPresent(locator, error_message, 10);

            // Проверяем видимость элемента
            if (!element.isDisplayed()) {
                throw new RuntimeException("Element is not visible on the screen");
            }

            // Получаем координаты элемента
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            // Логируем координаты
            System.out.println("Element coordinates: left_x=" + left_x + ", right_x=" + right_x + ", middle_y=" + middle_y);

            // Проверяем размеры элемента
            if (right_x - left_x <= 0 || lower_y - upper_y <= 0) {
                throw new RuntimeException("Element has invalid size or position");
            }

            if (Platform.getInstance().isAndroid()) {
                // Выполняем свайп влево
                PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
                Sequence swipe = new Sequence(finger, 1)
                        .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), right_x, middle_y))
                        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                        .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), left_x, middle_y))
                        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

                driver.perform(Collections.singletonList(swipe));
            } else {
                PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
                Sequence swipe = new Sequence(finger, 1)
                        .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), right_x, middle_y)) // Начальная точка
                        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // Нажатие
                        .addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), left_x, middle_y)) // Движение влево
                        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Отпускание

                driver.perform(Collections.singletonList(swipe));
            }
            // Ждем исчезновения элемента
            Thread.sleep(500); // Дополнительное ожидание
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to perform swipe left action", e);
        }
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public boolean isElementPresent(String locator) {
        return getAmountOfElements(locator) > 0;
    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts) {
        int current_attempts = 0;
        boolean need_more_attempts = true;

        while (need_more_attempts) {
            try {
                this.waitForElementAndClick(locator, error_message, 1);
                need_more_attempts = false;
            } catch (Exception e) {
                if (current_attempts > amount_of_attempts) {
                    this.waitForElementAndClick(locator, error_message, 1);
                }
            }
            ++ current_attempts;
        }
    }

    public int getAmountOfElements1(String locator) {
        try {
            // Используем явное ожидание для поиска элементов
            By by = this.getLocatorString(locator);
            WebDriverWait wait = new WebDriverWait(driver, 10); // Используем Duration.ofSeconds()
            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));

            // Возвращаем количество найденных элементов
            return elements.size();
        } catch (Exception e) {
            // Если элементы не найдены или произошла ошибка, возвращаем 0
            System.out.println("Error while finding elements: " + e.getMessage());
            return 0;
        }
    }

    public void assertElementNotPresent(String locator, String error_message) {
        int amount_of_elements = getAmountOfElements1(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + locator + "'supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSecond) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSecond);
        return element.getAttribute(attribute);
    }

    //Тема 3, ДЗ 2
    public void testAssertElementPresent(String locator) {
        try {
            By by = this.getLocatorString(locator);
            WebElement element = driver.findElement(by);
        } catch (Exception e) {
            throw new AssertionError("Элемент не найден: " + locator, e);
        }
    }

    private By getLocatorString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else if (by_type.equals("css")) {
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator " + locator_with_type);
        }
    }

    public String takeScreenshot(String name)
    {
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name + "_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken: " + path);
        } catch (Exception e) {
            System.out.println("Cannot take screenshot. Error: " + e.getMessage());
        }
        return path;
    }

    @Attachment
    public static byte[] screenshot(String path)
    {
        byte[] bytes = new byte[0];

        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Cannot get bytes from screenshot. Error: " + e.getMessage());
        }
        return bytes;
    }
}
