package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.gargoylesoftware.htmlunit.WebAssert.assertElementPresent;

public class ArticlePageObject extends MainPageObject {
    private final static String
            TITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']",
            SAVE_BUTTON = "id:org.wikipedia:id/page_save",
            ADD_TO_LIST = "id:org.wikipedia:id/snackbar_action",
            INPUT_BAR = "xpath://*[@resource-id='org.wikipedia:id/text_input']",
            OK_BUTTON = "id:android:id/button1",
            CLOSE_ARTICLE_BUTTON = "xpath://*[@content-desc='Navigate up']",
            READ_MORE_ARTICLE_SECTION = "xpath://*[@resource-id='pcs-footer-container-readmore-heading']",
            EXISTING_LIST = "xpath://*[@text='My new folder']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent((TITLE), "Can't find title", 5);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getText();
    }

    public void scrollTo(String text) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))"));


    }

    public void addArticleToMyList(String name_of_folder) {
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
//        this.waitForElementAndClick(
//                By.xpath("//android.widget.FrameLayout[@resource-id=\"org.wikipedia:id/container\"]"),
//                "Cannot create list",
//                3
//        );
        this.waitForElementAndClick(
                (INPUT_BAR),
                "Cannot find input bar",
                5
        );

        this.waitForElementAndSendKeys(
                (INPUT_BAR),
                name_of_folder,
                5
        );
        this.waitForElementAndClick(
                (OK_BUTTON),
                "Can't find Ok button",
                10
        );
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
    }

    public void assertTitle()
    {
        this.waitForElementPresent((TITLE), "Can't find article title", 0);
    }

}
