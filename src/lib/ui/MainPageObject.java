package lib.ui;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static lib.ui.MySavedListsPageObject.SWIPE_DELETE_BUTTON;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                presenceOfElementLocated(by)
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
        scrollPageDown();
        ++already_swiped;

    }

    public void scrollTillElementAppears(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)) {
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            scrollPageDown();
            ++already_swiped;
        }

    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(locator, "Can't find element by locator", 15).getLocation().getY();
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }


    public void testSwipe(String locator, String error_message) {
        WebElement element = this.waitForElementPresent(locator, "Can't find element", 10);
        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endX = (int) (size.getWidth() * 0.25);
        int endY = startY;
        int offset_x = (-1 * element.getSize().getWidth());
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, ofMillis(200)))
                .addAction(finger1.createPointerMove(ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }

    public void simpleSwipe(String locator) {
        WebElement element = waitForElementPresent(locator, "Can't find element to swipe", 10);

        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endX = (int) (size.getWidth() * 0.25);
        int endY = startY;
        int offset_x = (-1 * element.getSize().getWidth());
        int offset_y = (-1 * element.getSize().getHeight());


        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, ofMillis(2000)))
                .addAction(finger1.createPointerMove(ofMillis(1000), PointerInput.Origin.viewport(), endY, offset_x))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));

    }

    public void scrollPageDown() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endX = startX;
        int endY = (int) (size.getHeight() * 0.25);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, ofMillis(20)))
                .addAction(finger1.createPointerMove(ofMillis(50), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }


    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotFound(String locator, String error_message) {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 1) {
            String default_message = "An element '" + locator + "' not supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }

    }

    public String waitForElementAndGetText(String locator, String error_message, long timeOut) {
        WebElement element = waitForElementPresent(locator, error_message, timeOut);
        return element.getText();
    }

    public By getLocatorByString(String locator_with_type) {
        String[] explode_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = explode_locator[0];
        String locator = explode_locator[1];
        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else if (by_type.equals("name")) {
            return By.name(locator);
        } else {
            throw new IllegalArgumentException("Can't get type of locator Locator: " + locator_with_type);
        }
    }
        public void swipeToTheLeft(String locator) {
        WebElement element = this.waitForElementPresent(locator, "Can't find element", 10);

            JavascriptExecutor js = driver;
            Map<String, Object> params = new HashMap<>();
            params.put("direction", "left");
            params.put("element", ((RemoteWebElement) element).getId());
            js.executeScript("mobile: swipe", params);
            this.waitForElementPresent(SWIPE_DELETE_BUTTON,"Can't find Delete button");
        }
}

