import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class HomeTaskEx5 {
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
    public void saveFirstArticleToMyList() {
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

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cannot find title",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find Save button",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find Add to list button",
                2
        );
//        waitForElementAndClick(
//                By.xpath("//android.view.View[@resource-id=\"org.wikipedia:id/touch_outside\"]"),
//                "Cannot create list",
//                3
//       );
        waitForElementAndClick(
                By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]"),
                "Cannot find input bar",
                5
        );
        String name_of_folder = "My new list";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                5
        );
        waitForElementAndClick(
                By.id("android:id/button1"),
                "Can't find Ok button",
                10
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find Back button",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Clear query\"]"),
                "Can't find Clear button",
                10
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"org.wikipedia:id/search_src_text\"]"),
                "CSS",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@text=\"Style sheet language\"]"),
                "Can't find requested search result in the list",
                10
        );
        waitForElementPresent(
                By.xpath("//*[@text=\"Style sheet language\"]"),
                "Can't find title",
                10
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find Save button",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find Add to list button",
                2
        );

        waitForElementAndClick(
               By.xpath("//*[@text='My new list']"),
                "Can't find created list",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find Back button",
                10
        );
//        waitForElementAndClick(
//                By.id("android:id/button2"),
//                "Cannot skip pop-up",
//                10
//        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find Back button",
                10
        );

        waitForElementAndClick(
                By.xpath("(//android.widget.ImageView[@resource-id=\"org.wikipedia:id/navigation_bar_item_icon_view\"])[2]"),
                "Cannot find Saved button",
                10
        );

        waitForElementAndClick(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/recycler_view\"]/android.view.ViewGroup[2]"),
                "Cannot find saved listing",
                5
        );

        testSwipe(
                By.xpath("//*//*[@text=\"Style sheet language\"]"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text=\"Style sheet language\"]"),
                "Cannot find saved article",
                10
        );
        assertElementNotFound(
                By.xpath("//*[@text=\"Style sheet language\"]"),
                "Article is not deleted"
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

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutSeconds);
        element.clear();
        return element;
    }


    public void swipeElementToLeft(By by, String error_message) {

        WebElement element = waitForElementPresent(by, error_message, 10);

        Point location = element.getLocation();
        Dimension size = element.getSize();

        int left_x = location.getX();
        int right_x = left_x + size.getWidth();
        int upper_y = location.getY();
        int lower_y = upper_y + size.getHeight();
        int middle_y = upper_y + (size.getHeight() / 2);

        int start_x = right_x - 20;
        int end_x = left_x + 20;
        int start_y = middle_y;
        int end_y = middle_y;

        this.swipe(
                new Point(start_x, start_y),
                new Point(end_x, end_y),
                Duration.ofMillis(550)
        );
    }

    protected void swipe(Point start, Point end, Duration duration) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
    }

    protected void swipeElementToTheLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y).waitAction(150).moveTo(left_x, middle_y).release().perform();
    }

    protected void testSwipe(By by, String error_message)
    {
        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endX = (int)(size.getWidth() * 0.25);
        int endY = startY;
        PointerInput finger1 = new PointerInput (PointerInput.Kind. TOUCH,"finger1");
        Sequence sequence= new Sequence(finger1,1)
                .addAction (finger1.createPointerMove (Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction (finger1.createPointerDown(PointerInput.MouseButton. LEFT.asArg()))
                .addAction (new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove (Duration.ofMillis(100), PointerInput. Origin.viewport(), endX, endY))
                .addAction (finger1.createPointerUp (PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }
    protected void simpleSwipe()
    {
        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endX = (int)(size.getWidth() * 0.25);
        int endY = startY;
        PointerInput finger1 = new PointerInput (PointerInput.Kind. TOUCH,"finger1");
        Sequence sequence= new Sequence(finger1,1)
                .addAction (finger1.createPointerMove (Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction (finger1.createPointerDown(PointerInput.MouseButton. LEFT.asArg()))
                .addAction (new Pause(finger1, Duration.ofMillis(2000)))
                .addAction(finger1.createPointerMove (Duration.ofMillis(1000), PointerInput. Origin.viewport(), endX, endY))
                .addAction (finger1.createPointerUp (PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
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
