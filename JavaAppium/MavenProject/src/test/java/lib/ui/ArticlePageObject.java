package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            SAVE_BUTTON,
            ADD_TO_LIST,
            ADD_NEW_LIST,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            MY_EXISTING_LIST,
            CLOSE,
            LATER,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON;


    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    @Step("Get existing list name")
    public static String getExistingListElement(String substring)
    {
        return MY_EXISTING_LIST.replace("{LIST_NAME}", substring);
    }
    /* TEMPLATES METHODS */

    @Step("Waiting for title on the article page")
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article on page", 15);
    }

    @Step("Get article title")
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    @Step("Swiping to footer on article page")
    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        } else if (Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                "Cannot find the end of article",
                40
            );
        } else {
            this.scrollWebPageTitleElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }
    }

    @Step("Adding the article to my list")
    public void arcticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find Save",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_LIST,
                "Cannot find Add to list",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into article folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    @Step("Adding the article to existing list")
    public void articleToExistingList(String name_of_folder)
    {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find Save",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_LIST,
                "Cannot find Add to list",
                5
        );

        String existing_list = getExistingListElement(name_of_folder);
        this.waitForElementAndClick(
                existing_list,
                "Cannot find 'New create list'",
                5
        );
    }

    @Step("Adding the article to my saved list")
    public void addArticleToNySaved(String name_of_folder)
    {
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find Save",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_LIST,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                ADD_NEW_LIST,
                "Cannot find button 'Add new list'",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into article folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find 'OK' button",
                5
        );
    }

    @Step("Removing the article from saved if it has been added")
    public void removeArticleFromSavedIfItAdded ()
    {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
           this.waitForElementAndClick(
                   OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                   "Cannot click button to remove an article from saved",
                   1
           );
           this.waitForElementPresent(
                   OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                   "Cannot find button to add an article to saved list after removing it this list before"
           );
        }
    }
}
