package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import lib.ui.NavigationUI;

@Epic("Tests for creating a folder and adding articles")
public class MyListsTests extends CoreTestCase
{
    public static final String name_of_folder = "New create list";
    private static final String
            login = "TanyaTest",
            password = "lozovskaya";

    @Test // Создание папки сохраненных, добавление и удаление статьи
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Create add and delete article")
//    @Description("We create a folder, add an article and delete it")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.arcticleToMyList(name_of_folder);

            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.snackbarAction();
        } else  {
            ArticlePageObject.addArticleToNySaved(name_of_folder);
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        MyListPageObject.HasElement();

        MyListPageObject.swipeByArticleToDelete("Java (programming language)");
        Assert.assertFalse("Статья не должна быть в списке после удаления",
                MyListPageObject.isArticlePresent(article_title));
    }

    // Ex5 (Тема 5) (Тема 7) (Тема 10)
    @Test // Создание папки сохраненных, добавление и удаление статьи
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Create adding several articles and deleting one of them")
//    @Description("We create a folder, add two articles, delete one of them")
    @Step("Starting test testSaveToArticleToMyListAndDeleteOne")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSaveToArticleToMyListAndDeleteOne()
    {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java Rush"); // Java
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language"); //

        ArticlePageObject articlePage = ArticlePageObjectFactory.get(driver);
        String firstArticleTitle = articlePage.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePage.arcticleToMyList(name_of_folder);
        } else {
            articlePage.addArticleToNySaved(name_of_folder);
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.backButton(); // нажать "Назад"

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(login, password);
            auth.submitForm();

            articlePage.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login",
                    firstArticleTitle,
                    articlePage.getArticleTitle());
        }

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Фреймворк");
        searchPageObject.clickByArticleWithSubstring("Фреймворк");

        String secondArticleTitle = "Фреймворк";

        if (Platform.getInstance().isAndroid()) {
            articlePage.articleToExistingList(name_of_folder);
        } else {
            articlePage.addArticleToNySaved(name_of_folder);
        }

        navigationUI.openNavigation();
        navigationUI.clickMyLists();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        myListPageObject.HasElement();

        if (Platform.getInstance().isAndroid()) {
            myListPageObject.swipeByArticleToDelete(secondArticleTitle);
        } else {
            myListPageObject.swipeByArticleToDeleteForIOS(secondArticleTitle);
            myListPageObject.hasFootersTextByArticle();
        }

        Assert.assertFalse("Вторая статья должна быть удалена",
                myListPageObject.isArticlePresent(secondArticleTitle));

        Assert.assertTrue("Первая статья должна остаться в списке",
                myListPageObject.isArticlePresent(firstArticleTitle));
    }
}
