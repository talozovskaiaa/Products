package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject
{
     static {
         TITLE = "css:.mw-page-title-main";
         FOOTER_ELEMENT = "css:.mw-footer.minerva-footer";
         SAVE_BUTTON = "css:#ca-watch"; // "Сохранить"
         OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:.minerva-icon.minerva-icon--unStar";
//         ADD_TO_LIST = "xpath://XCUIElementTypeImage[@name='add-to-list']"; // добавить в список для чтения
//         ADD_NEW_LIST = "xpath://XCUIElementTypeButton[@name='Создать новый список']"; // создать новый список в избранном
//         MY_LIST_NAME_INPUT = "xpath://XCUIElementTypeTextField[@value='заголовок списка для чтения']"; // название папки избранного
//         MY_LIST_OK_BUTTON = "xpath://XCUIElementTypeButton[@name='Создать список для чтения']"; // нажатие на создание папки избранного
//         MY_EXISTING_LIST = "xpath://XCUIElementTypeButton[@name='{LIST_NAME}']"; // переход на созданную папку избранного
//         CLOSE = "xpath://XCUIElementTypeButton[@name='Назад']";
//         LATER = "xpath://XCUIElementTypeButton[@name='chevron right']";
     }

    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
