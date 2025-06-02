package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "xpath:(//XCUIElementTypeStaticText[@name='Java'])[1]";
        FOOTER_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='Просмотреть статью в браузере']";
        SAVE_BUTTON = "xpath://XCUIElementTypeButton[@name='Сохранить на потом']"; // нижний таббар "Сохранить"
        ADD_TO_LIST = "xpath://XCUIElementTypeImage[@name='add-to-list']"; // добавить в список для чтения
        ADD_NEW_LIST = "xpath://XCUIElementTypeButton[@name='Создать новый список']"; // создать новый список в избранном
        MY_LIST_NAME_INPUT = "xpath://XCUIElementTypeTextField[@value='заголовок списка для чтения']"; // название папки избранного
        MY_LIST_OK_BUTTON = "xpath://XCUIElementTypeButton[@name='Создать список для чтения']"; // нажатие на создание папки избранного
        MY_EXISTING_LIST = "xpath://XCUIElementTypeButton[@name='{LIST_NAME}']"; // переход на созданную папку избранного
        CLOSE = "xpath://XCUIElementTypeButton[@name='Назад']";
        LATER = "xpath://XCUIElementTypeButton[@name='chevron right']";
    }

    public IOSArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
