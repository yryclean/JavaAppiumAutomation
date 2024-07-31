package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MySavedListsPageObject extends MainPageObject {

    public static final String
    FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
    ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    private static String getFolderByXpathName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getTitleByXpathName(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    public MySavedListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    public void openFolderByName(String name_of_folder)
    {   String folder_name_xpath = getFolderByXpathName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find saved folder by name " + name_of_folder,
                5
        );
    }
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getFolderByXpathName(article_title);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "Cannot find saved article " + article_title,
                10
        );
    }
    public void waitForArticleTitleToDisappear(String article_title)
    {
        String article_xpath = getFolderByXpathName(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Saved article is still displayed " + article_title,
                10
        );
    }
    public void swipeArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getFolderByXpathName(article_title);
        this.testSwipe(
                By.xpath(article_xpath),
                "Cannot find saved article"
        );
        this.waitForArticleTitleToDisappear(article_title);
    }

    public void openArticleFromFolder(String article_title)
    {
        String article_xpath = getTitleByXpathName(article_title);
        this.waitForElementAndClick(
                By.xpath(article_xpath),
                "Cannot open saved list from folder",
                5
        );
    }

}
