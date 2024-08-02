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

public class MainPageObject {
    protected AppiumDriver driver;
    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }
    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, value, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutSeconds);
        element.clear();
        return element;
    }

    public void swipeUpQuick() {
        swipeUpQuick();

    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes)
                waitForElementPresent(by, "Cannot find element by swipe. \n" + error_message, 0);
            return;
        }
        swipeElementToLeft(by, "Cannot swipe");
        ++already_swiped;

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

    public void swipe(Point start, Point end, Duration duration) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
    }

    public void testSwipe(By by, String error_message)
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

    public int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotFound(By by,String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 1) {
            String default_message = "An element '" + by.toString() + "' not supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }

    }
    public String waitForElementAndGetText(By by, String error_message, long timeOut)
    {
        WebElement element = waitForElementPresent(by, error_message,timeOut);
        return element.getText();
    }
}
