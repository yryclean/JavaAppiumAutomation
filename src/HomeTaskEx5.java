
import lib.CoreTestCase;
import org.junit.Test;
import lib.ui.SearchPageObject;
import lib.ui.OnboardingPageObject;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.MySavedListsPageObject;



public class HomeTaskEx5 extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {

        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        String name_of_folder = "My new folder";
        String article_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Unexpected title!",
                "Object-oriented programming language",
                article_title
        );
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
        SearchPageObject.typeSearchLine("CSS");
        SearchPageObject.clickByArticleWithSubstring("Style sheet language");
        ArticlePageObject.waitForTitleElement();
        String second_article_title = ArticlePageObject.getArticleTitle();

        ArticlePageObject.addArticleToExistingList();
        ArticlePageObject.closeArticle();
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickSavedList();
        MySavedListsPageObject MySavedListsPageObject = new MySavedListsPageObject(driver);
        MySavedListsPageObject.openFolderByName(name_of_folder);

        MySavedListsPageObject.swipeArticleToDelete(second_article_title);

        MySavedListsPageObject.openArticleFromFolder(article_title);
        ArticlePageObject.waitForTitleElement();
    }
}
