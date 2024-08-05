package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

import static java.lang.System.getenv;

public class CoreTestCase extends TestCase {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";


    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.getCapabilitiesByPlatformEnv();
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("appium:platformVersion", "14.0");
            capabilities.setCapability("appium:appPackage", "org.wikipedia");
            capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
            capabilities.setCapability("appium:appActivity", ".main.MainActivity");
            capabilities.setCapability("appium:automationName", "UiAutomator2");
            capabilities.setCapability("app", "/Users/lifetech/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/Wiki.apk");
            driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("appium:platformVersion", "17.4");
            capabilities.setCapability("appium:deviceName", "iPhone 14 Pro");
            capabilities.setCapability("appium:automationName", "XCUITest");
            capabilities.setCapability("app", "/Users/lifetech/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/Wikipedia.app");
            driver = new IOSDriver(new URL(AppiumURL), capabilities);
        } else {
            throw new Exception("Can't get platform env variable. Platform value " + platform);
        }
        return capabilities;
    }
}