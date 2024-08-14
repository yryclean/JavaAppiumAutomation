
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MySavedListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import lib.ui.SearchPageObject;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.MySavedListsPageObject;



public class HomeTaskEx5 extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        String name_of_folder = "My new folder";
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
            SearchPageObject.waitForCancelButtonToDisappear();
        } else {
            SearchPageObject.clickCancelSearch();
        }
        SearchPageObject.initSearchInput();
        String title2 = SearchPageObject.typeSearchLine("CSS");
        SearchPageObject.clickByArticleWithSubstring("Style sheet language");
        ArticlePageObject.waitForArticleTitle(title2);
            assertEquals(
                    "Unexpected title!",
                    "CSS",
                    title2
            );
        ArticlePageObject.addArticleToExistingList();
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
            SearchPageObject.waitForCancelButtonToDisappear();
        } else {
            SearchPageObject.clickCancelSearch();
        }
        MySavedListsPageObject MySavedListsPageObject = MySavedListsPageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            NavigationUI.clickSavedList();
            MySavedListsPageObject.openFolderByName(name_of_folder);
            MySavedListsPageObject.swipeArticleToDelete(title2);
            MySavedListsPageObject.openArticleFromFolder(article_title);
            ArticlePageObject.waitForTitleElement();
        } else {
            NavigationUI.clickSavedList();
            MySavedListsPageObject.closeSyncPopUp();
            MySavedListsPageObject.swipeArticleToDelete(article_title);
            ArticlePageObject.waitForArticleTitle(title2);
            MySavedListsPageObject.openSavedArticle(title2);
        }
    }

}
