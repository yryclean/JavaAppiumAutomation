package lib.ui;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.WebElement;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE,
            TITLE_BY_TPL,
            SAVE_BUTTON,
            ADD_TO_LIST,
            INPUT_BAR,
            OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            READ_MORE_ARTICLE_SECTION,
            EXISTING_LIST,
            CREATE_LIST;


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {

        return this.waitForElementPresent((TITLE), "Can't find title", 5);
    }
    private static String getArticleTitleByXpathName(String title)
    {
        return TITLE_BY_TPL.replace("{TITLE}", title);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getText();
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void scrollTo() {
        if (Platform.getInstance().isAndroid()) {

            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + READ_MORE_ARTICLE_SECTION + "\").instance(0))"));
        } else  {
            this.scrollTillElementAppears(READ_MORE_ARTICLE_SECTION, "Can't find section", 30);
        }

    }

    public void addArticleToMyList(String name_of_folder) {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    (SAVE_BUTTON),
                    "Cannot find Save button",
                    10
            );
            this.waitForElementAndClick(
                    (ADD_TO_LIST),
                    "Cannot find Add to list button",
                    10
            );

            this.waitForElementAndClick(
                    (INPUT_BAR),
                    "Cannot find input bar",
                    10
            );

            this.waitForElementAndSendKeys(
                    (INPUT_BAR),
                    name_of_folder,
                    10
            );
            this.waitForElementAndClick(
                    (OK_BUTTON),
                    "Can't find Ok button",
                    10
            );
        } else {
            this.waitForElementAndClick(
                    SAVE_BUTTON,
                    "Cannot find Save button",
                    10
            );
            this.waitForElementAndClick(
                    (ADD_TO_LIST),
                    "Cannot find Add to list button",
                    10
            );
            this.waitForElementAndClick(
                    CREATE_LIST,
                    "Can't create list",
                    10
            );
            this.waitForElementAndClick(
                    (INPUT_BAR),
                    "Cannot find input bar",
                    10
            );
            this.waitForElementAndSendKeys(
                    (INPUT_BAR),
                    name_of_folder,
                    10
            );
            this.waitForElementAndClick(
                    (OK_BUTTON),
                    "Can't find Create list button button",
                    10
            );
        }
    }

    public void addArticleToSaved()
    {
        this.waitForElementAndClick(SAVE_BUTTON, "Can't find save button", 10);
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                (CLOSE_ARTICLE_BUTTON),
                "Cannot find Back button",
                10
        );
    }


    public void findArticleReadMore() {
        this.waitForElementPresent(
                (READ_MORE_ARTICLE_SECTION),
                "Can't find article read more section",
                15
        );
    }

    public void addArticleToExistingList() {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    (SAVE_BUTTON),
                    "Cannot find Save button",
                    5
            );
            this.waitForElementAndClick(
                    (ADD_TO_LIST),
                    "Cannot find Add to list button",
                    2
            );

            this.waitForElementAndClick(
                    (EXISTING_LIST),
                    "Cannot find existing list",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    (SAVE_BUTTON),
                    "Cannot find Save button",
                    5
            );
        }

    }
    public void assertTitle()
    {
        this.waitForElementPresent((TITLE), "Can't find article title", 0);
    }

    public void waitForArticleTitle(String article_title) {
        String article_xpath = getArticleTitleByXpathName(article_title);
        this.waitForElementPresent(
                (article_xpath),
                "Cannot find saved article " + article_title,
                10
        );
    }

}
