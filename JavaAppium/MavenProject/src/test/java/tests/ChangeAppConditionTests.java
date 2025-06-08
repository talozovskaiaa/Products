package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic(value = "Screen rotation tests")
public class ChangeAppConditionTests extends CoreTestCase {
//
//    @Test // Тест на сравнение статей при повороте экране
//    @DisplayName("Compare article title with expected one when you rotate the screen")
//    @Description("Open the article 'Java', rotate the screen and check that the title matches the expected one")
//    @Step("Starting test testChangesScreenOrientationOnSearchResults")
//    @Severity(value = SeverityLevel.MINOR)
//    public void testChangesScreenOrientationOnSearchResults()
//    {
//        if (Platform.getInstance().isMW()) {
//            return;
//        }
//        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
//
//        SearchPageObject.initOndoardingInput();
//        SearchPageObject.initSearchInput();
//        SearchPageObject.typeSearchLine("Java");
//        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
//
//        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
//        String title_before_rotation = ArticlePageObject.getArticleTitle();
//        this.rotateScreenLandscape();
//        String title_after_rotation = ArticlePageObject.getArticleTitle();
//
//        Assert.assertEquals(
//                "Article title have been changed after screen rotation",
//                title_before_rotation,
//                title_after_rotation
//        );
//
//        this.rotateScreenPortrait();
//        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
//
//        Assert.assertEquals(
//                "Article title have been changed after screen rotation",
//                title_before_rotation,
//                title_after_second_rotation
//        );
//    }
//
//    @Test //сворачивать приложение и возвращаться к нему снова
//    @DisplayName("Minimize the application and return to it")
//    @Description("We minimized the application and returned to it")
//    @Step("Starting test testCheckSearchArticleInBackround")
//    @Severity(value = SeverityLevel.NORMAL)
//    public void testCheckSearchArticleInBackround()
//    {
//        if (Platform.getInstance().isMW()) {
//            return;
//        }
//        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
//
//        SearchPageObject.initOndoardingInput();
//        SearchPageObject.initSearchInput();
//        SearchPageObject.typeSearchLine("Java");
//        SearchPageObject.waitForSearchResult("Object-oriented programming language");
//        this.backgroundApp(2);
//        SearchPageObject.waitForSearchResult("Object-oriented programming language");
//    }
}
