
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:platformVersion", "14.0");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("app", "/Users/lifetech/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/Wiki.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]"),
                "Cannot find Skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text=\"Search Wikipedia\"]"),
                "Cannot find Search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"org.wikipedia:id/search_src_text\"]"),
                "Java",
                5
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\" and @text=\"Object-oriented programming language\"]"),
                "Results for Java cannot be found",
                5
        );
    }

    @Test
    public void testCancelSearch() {

        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"org.wikipedia:id/search_src_text\"]"),
                "Java",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search",
                5
        );
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/search_close_btn"),
//                "Cannot find clear button",
//                5
//        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Clear button is not hidden",
                5
        );
    }
    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"org.wikipedia:id/search_src_text\"]"),
                "Java",
                5
        );
        waitForElementAndClick(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup[2]"),
                "Cannot find such a result in search",
                5
        );

        WebElement title_element = waitForElementPresent(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cannot find title",
                5
        );
        String article_title = title_element.getText();
        Assert.assertEquals(
                "Unexpected title!",
                "Java (programming language)",
                article_title
        );

    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, value, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    private WebElement waitForElementAndClear(By by, String error_message, long timeoutSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutSeconds);
        element.clear();
        return element;
    }
}