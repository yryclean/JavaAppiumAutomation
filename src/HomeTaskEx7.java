import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.List;


public class HomeTaskEx7
{
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

        ScreenOrientation orientation = driver.getOrientation();
        if (orientation.equals(ScreenOrientation.LANDSCAPE)) {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }

    }

    @After
    public void tearDown() {

        driver.quit();
    }

    @Test
    public void rotateOnboarding()
    {
        String before_rotate = waitForElementAndGetText(
                By.xpath("//*[@text='We’ve found the following on your device:']"),
                "Can't find main title",
                10
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String after_rotate = waitForElementAndGetText(
                By.xpath("//*[@text='We’ve found the following on your device:']"),
                "Can't find title after rotation",
                15
        );
        Assert.assertEquals(
                "Title changed after rotation",
                before_rotate,
                after_rotate
        );
//        driver.rotate(ScreenOrientation.PORTRAIT);
//
//        String after_second_rotate = waitForElementAndGetText(
//                By.xpath("//*[@text='We’ve found the following on your device:']"),
//                "Can't find such result for",
//                15
//        );
//        Assert.assertEquals(
//                "Title changed after second rotation",
//                before_rotate,
//                after_second_rotate
//        );
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


    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotFound(By by,String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 1) {
            String default_message = "An element '" + by.toString() + "' not supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }

    }
    private String waitForElementAndGetText(By by, String error_message, long timeOut)
    {
        WebElement element = waitForElementPresent(by, error_message,timeOut);
        return element.getText();
    }


}
