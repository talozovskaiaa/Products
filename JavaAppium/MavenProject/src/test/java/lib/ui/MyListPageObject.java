package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.NoSuchElementException;


abstract public class MyListPageObject extends MainPageObject {

    protected static String
            XPATHELEMENT,
            ARTICLE_BY_TITLE_TMP,
            REMOVE_FROM_SAVED_BUTTON;

    /* TEMPLATES METHODS */
    private static String getSaveArticleXpathByArticle(String article_title) {
        String escapedTitle = article_title.replace("'", "\\'"); // Экранирование одинарных кавычек
        return ARTICLE_BY_TITLE_TMP.replace("{TITLE}", escapedTitle);
    }
    /* TEMPLATES METHODS */

    private static String getRemoveButtonByTitle(String article_title) {
        String title = article_title.replace("'", "\\'");
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", title);
    }

    public MyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void HasElement()
    {
        this.waitForElementPresent(
                XPATHELEMENT,
                "Cannot find element",
                2
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSaveArticleXpathByArticle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find article by title" + article_title,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSaveArticleXpathByArticle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still " + article_title,
                3
        );
    }

    public void swipeByArticleToDelete(String article_title) {

        if (article_title == null || article_title.isEmpty()) {
            throw new IllegalArgumentException("Article title cannot be null or empty");
        }

        this.waitForArticleToAppearByTitle(article_title); // Передаем текст, а не XPath
        String article_xpath = getSaveArticleXpathByArticle(article_title);
        System.out.println("Generated XPath: " + article_xpath);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            try {
                this.swipeElementToLeft(article_xpath, "Cannot swipe left on article with title: " + article_title);
            } catch (Exception e) {
                throw new RuntimeException("Failed to perform swipe action on article with title: " + article_title, e);
            }
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }

        if (Platform.getInstance().isIOS()) {
            try {
                this.clickElementInTheRightUpperCorner(article_xpath, "Cannot find saved article with title: " + article_title);
            } catch (Exception e) {
                throw new RuntimeException("Failed to click element in the right upper corner for iOS", e);
            }
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void swipeByArticleToDeleteForIOS(String article_title) {
        if (article_title == null || article_title.isEmpty()) {
            throw new IllegalArgumentException("Article title cannot be null or empty");
        }

        this.waitForArticleToAppearByTitle(article_title); // Передаем текст, а не XPath
        String article_xpath = getSaveArticleXpathByArticle(article_title);
        System.out.println("Generated XPath: " + article_xpath);

        try {
            this.swipeElementToLeft(article_xpath, "Cannot swipe left on article with title: " + article_title);
        } catch (Exception e) {
            throw new RuntimeException("Failed to perform swipe action on article with title: " + article_title, e);
        }

        if (Platform.getInstance().isIOS()) {
            try {
                this.clickElementInTheRightUpperCorner(article_xpath, "Cannot find saved article with title: " + article_title);
            } catch (Exception e) {
                throw new RuntimeException("Failed to click element in the right upper corner for iOS", e);
            }
        }
    }

    public void hasFootersTextByArticle()
    {
        this.swipeUpToFindElement("//XCUIElementTypeStaticText[@name='компьютерная игра 2016 года']", "cannot find footer's text", 40);
    }

        // Метод для проверки наличия статьи в списке
        public boolean isArticlePresent(String articleTitle) {
            try {
                WebElement articleElement = driver.findElement(By.xpath("//div[@class='article-item' and contains(text(), '" + articleTitle + "')]"));
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }
}
