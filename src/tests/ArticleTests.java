package tests;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MySavedListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class ArticleTests extends CoreTestCase
{
    private static final String name_of_folder = "My new folder";
    @Test
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Unexpected title!",
                "Java (programming language)",
                article_title
        );

    }

    @Test
    public void testArticleSwipe() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.scrollTo();
        ArticlePageObject.findArticleReadMore();

    }
    @Test
    public void testSaveFirstArticleToMyList() {
        MySavedListsPageObject MySavedListsPageObject = MySavedListsPageObjectFactory.get(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Unexpected title!",
                "Java (programming language)",
                article_title
        );

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
        } else {
            SearchPageObject.clickCancelSearch();
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            NavigationUI.clickSavedList();
            MySavedListsPageObject.openFolderByName(name_of_folder);
        } else {
            NavigationUI.clickSavedList();
            MySavedListsPageObject.closeSyncPopUp();
        }
        MySavedListsPageObject.swipeArticleToDelete(article_title);

    }

    @Test
    public void testRotateSearchArticleBasic()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Title changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Title changed after second rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckArticleNameAfterBackground()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testSimpleAddTest() throws InterruptedException {
        MySavedListsPageObject MySavedListsPageObject = MySavedListsPageObjectFactory.get(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.saveArticleForLaterSearchPage();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickSavedList();
        MySavedListsPageObject.closeSyncPopUp();
        MainPageObject MainPageObject = new MainPageObject(driver);
        String locator = "xpath://XCUIElementTypeStaticText[@name='Flag of Japan']";
        MainPageObject.swipeToTheLeft(locator);
        String delete = "id:swipe action delete";
        MainPageObject.waitForElementAndClick(delete, "Can't find delete button", 10);



    }
}


