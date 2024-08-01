package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "//*[@text='Search Wikipedia']",
        SEARCH_INPUT = "//*[@text='Search Wikipedia']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRING}']",
    SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{SUBSTRING}']",
    SEARCH_RESULT_ARTICLES = "//*[@resource-id='org.wikipedia:id/search_results_list']",
    SEARCH_EMPTY_RESULT = "//*[@text='No results']",
    SEARCH_INPUT_PAGE = "org.wikipedia:id/search_empty_message";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    /*TEMPLATES METHODS */
    public static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    public static String getResultSearchElementTitle(String substring)
    {
        return SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }


    /*TEMPLATES METHODS */

    public void initSearchInput()

    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Can't find and click search element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Can't find search element", 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON),"Can't find Cancel button", 5);

    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),"Cancel button is not hidden", 5);

    }

    public void clickCancelSearch()
    {
        waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),"Can't find and click search cancel button", 5);
    }


    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line,5);

    }
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Can't find search result with substring " + substring, 5);
    }
    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Can't find and click search result with substring " + substring, 5);
    }
    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ARTICLES),
                "Can't find search results ",
                10
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ARTICLES));
    }
    public void waitForEmptySearch()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT), "Can't find empty search", 10);
    }
    public void assertEmptySearchResult()
    {
        this.assertElementNotFound(By.xpath(SEARCH_RESULT_ARTICLES), "No search results are expected");
    }
    public void waitForSearchInput()
    {
        this.waitForElementPresent(By.id(SEARCH_INPUT_PAGE), "Can't find empty search field", 10);
    }
    public void assertEmptySearchInput()
    {
        this.waitForElementPresent(By.id(SEARCH_INPUT_PAGE), "Search field is expected to be displayed", 10);

    }
    public void waitForSearchResultTitle(String substring)
    {
        String search_result_xpath = getResultSearchElementTitle(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Can't find search result with substring " + substring, 5);
    }
    public void waitForElementByTitleAndDescription(String title, String description)
    {
        this.waitForSearchResultTitle(title);
        this.waitForSearchResult(description);
    }
}
