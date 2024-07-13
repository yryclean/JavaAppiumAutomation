
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
    public void testCompareArticleTitle() {
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

    @Test
    public void testArticleSwipe() {
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

    }

    @Test
    public void swipeOnboarding() {

//        waitForElementAndClick(
//                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]"),
//                "Cannot find Skip button",
//                5
//        );

        simpleSwipe();

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
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@resource-id=\"org.wikipedia:id/container\"]"),
                "Cannot create list",
                3
        );
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
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article",
                10
        );

    }
    @Test
    public void testOfAmountNotEmptySearch()
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
        String search_input = "Linkin park discography";
        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"org.wikipedia:id/search_src_text\"]"),
                search_input,
                5
        );
        String search_result = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]";
        waitForElementPresent(
                By.xpath(search_result),
                "Can't find search results " + search_input,
                10
        );
        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result)
        );
        Assert.assertTrue(
                "Amount of search results is less than expected",
                amount_of_search_results > 0
        );
    }
    @Test
    public void testAmountOfEmptySearch()
    {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]"),
                "Cannot find Skip button",
                10
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search input",
                10
        );
        String search_input = "rfeurhguegerhughure";
        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"org.wikipedia:id/search_src_text\"]"),
                search_input,
                10
        );
        String search_result = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]";
        String empty_search = "//*[@text='No results']";
        waitForElementPresent(
                By.xpath(empty_search),
                "Can't find empty search results " + search_input,
                10
        );

        assertElementNotFound(
                By.xpath(search_result),
                "Some results were found"
        );
    }
    @Test
    public void rotateSearchArticleBasic()
    {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]"),
                "Cannot find Skip button",
                10
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search input",
                10
        );
        String search_input = "Java";
        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"org.wikipedia:id/search_src_text\"]"),
                search_input,
                10
        );
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\" and @text=\"Object-oriented programming language\"]"),
                "Cannot find Search input",
                10
        );

        String search_result = "//android.widget.TextView[@text=\"Java (programming language)\"]";
        waitForElementPresent(
                By.xpath(search_result),
                "Can't find search results for " + search_input,
                10
        );

        String title_before_rotation = waitForElementAndGetText(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Can't find such result for " + search_result,
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation = waitForElementAndGetText(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Can't find such result for " + search_result,
                15
        );
        Assert.assertEquals(
                "Title changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = waitForElementAndGetText(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Can't find such result for " + search_result,
                15
        );
        Assert.assertEquals(
                "Title changed after second rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }
    @Test
    public void checkArticleNameAfterBackground()
    {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]"),
                "Cannot find Skip button",
                10
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search input",
                10
        );
        String search_input = "Java";
        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"org.wikipedia:id/search_src_text\"]"),
                search_input,
                10
        );
        waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\" and @text=\"Object-oriented programming language\"]"),
                "Cannot find such article " + search_input,
                10
        );
        driver.runAppInBackground(2);
        waitForElementPresent(
                By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\" and @text=\"Object-oriented programming language\"]"),
                "Cannot find such article name after background " + search_input,
                10
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

    protected void swipeUpQuick() {
        swipeUpQuick();

    }

//    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
//        int already_swiped = 0;
//        while (driver.findElements(by).size() == 0) {
//            if (already_swiped > max_swipes)
//                waitForElementPresent(by, "Cannot find element by swipe. \n" + error_message, 0);
//            return;
//        }
//        swipeElementToLeft(by, "Cannot swipe");
//        ++already_swiped;
//
//    }

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