package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;
    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }
    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, value, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutSeconds);
        element.clear();
        return element;
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes)
                waitForElementPresent(locator, "Cannot find element by swipe. \n" + error_message, 0);
            return;
        }
        swipeElementToLeft(locator, "Cannot swipe");
        ++already_swiped;

    }

    public void swipeElementToLeft(String locator, String error_message) {

        WebElement element = waitForElementPresent(locator, error_message, 10);

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

    public void swipe(Point start, Point end, Duration duration) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
    }

    public void testSwipe(String locator, String error_message)
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
    public void simpleSwipe()
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
    public void scrollPageDown()
    {
        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endX = startX;
        int endY = (int) (size.getHeight() * 0.25);
        PointerInput finger1 = new PointerInput (PointerInput.Kind. TOUCH,"finger1");
        Sequence sequence= new Sequence(finger1,1)
                .addAction (finger1.createPointerMove (Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction (finger1.createPointerDown (PointerInput.MouseButton. LEFT.asArg()))
                .addAction (new Pause (finger1, Duration.ofMillis(20)))
                .addAction(finger1.createPointerMove (Duration.ofMillis(100), PointerInput. Origin.viewport(), endX, endY))
                .addAction (finger1.createPointerUp (PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));

    }

    public int getAmountOfElements(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotFound(String locator,String error_message)
    {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 1) {
            String default_message = "An element '" + locator + "' not supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }

    }
    public String waitForElementAndGetText(String locator, String error_message, long timeOut)
    {
        WebElement element = waitForElementPresent(locator, error_message,timeOut);
        return element.getText();
    }

    public By getLocatorByString(String locator_with_type)
    {
        String[] explode_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = explode_locator[0];
        String locator = explode_locator[1];
        if(by_type.equals("xpath")){
            return By.xpath(locator);
        } else if(by_type.equals("id")) {
            return By.id(locator);
        } else if(by_type.equals("name")) {
            return By.name(locator);
        } else {
            throw new IllegalArgumentException("Can't get type of locator Locator: " + locator_with_type);
        }
    }
}
