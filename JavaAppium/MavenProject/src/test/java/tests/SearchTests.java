package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Test for search articles")
public class SearchTests extends CoreTestCase {

    @Test // Тест на поиск названия поисковой строки
    @Feature(value = "Search")
    @DisplayName("Search article 'Java'")
//    @Description("We search article 'Java'")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch()
    {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test // Тест по очистке введенного слова и возращения назад
    @Feature(value = "Search")
    @DisplayName("Enter 'Java' in the line, clear it and go back")
//    @Description("We enter 'Java' in the line, clear it and go back")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelToDisappear();
    }


    @Test // Тест по добавлению Accert
    @Feature(value = "Search")
    @DisplayName("Count number of articles")
//    @Description("We count the number of articles")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Check the text if there are no articles")
//    @Description("We check the text if there are no articles")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "zxcvbnmddceh";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNotResultOfSearch();
    }

    // Ex3 (5 Тема)
    @Test
    @Feature(value = "Search")
    @DisplayName("Enter 'Java', search for two articles, clear it and go back")
//    @Description("We enter 'Java', search for two articles, clear it and go back")
    @Step("Starting test testCancel_search")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancel_search()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Russia";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult("Country spanning Europe and Asia");
        SearchPageObject.waitForSearchResult("East Slavic language");
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelToDisappear();

    }

    // Ex6 (5 Тема)
    @Test // Тест на проверку title
    @Feature(value = "Search")
    @DisplayName("Open article 'Java' and compare the header")
//    @Description("We open article 'Java' and compare the header")
    @Step("Starting test testAssertElementPresent")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testAssertElementPresent() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        SearchPageObject.waitForResultArticle();


    }

}
