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

public class HomeTask1 {

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
        public void testClearSearch() {

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
                    "Quality assurance",
                    5
            );
            waitForElementPresent(
                    By.id("org.wikipedia:id/search_results_list"),
                    "No results for such query",
                    5
            );

            waitForElementAndClear(
                    By.id("org.wikipedia:id/search_src_text"),
                    "Cannot find search",
                    5
            );

            WebElement search_empty = waitForElementPresent(
                    By.id("org.wikipedia:id/search_empty_message"),
                    "Search results are not cleared",
                    5
            );
            String empty = search_empty.getText();
            Assert.assertEquals(
                    "Search is not empty",
                    "Search Wikipedia in more languages",
                    empty
);
            System.out.println("Test is passed");

        }
        private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.withMessage(error_message + "\n");
            return wait.until(
                    ExpectedConditions.presenceOfElementLocated(by)
            );
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

        private WebElement waitForElementAndClear(By by, String error_message, long timeoutSeconds)
        {
            WebElement element = waitForElementPresent(by, error_message, timeoutSeconds);
            element.clear();
            return element;
        }
}
