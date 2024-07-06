
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appium:platformVersion","14.0");
        capabilities.setCapability("appium:appPackage","org.wikipedia");
        capabilities.setCapability("appium:deviceName","AndroidTestDevice");
        capabilities.setCapability("appium:appActivity",".main.MainActivity");
        capabilities.setCapability("appium:automationName","UiAutomator2");
        capabilities.setCapability("app","/Users/lifetech/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/Wiki.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest()
    {
        driver.findElement(By.id("org.wikipedia:id/fragment_onboarding_skip_button")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Search Wikipedia\"]")).click();
        driver.findElement(By.id("org.wikipedia:id/search_src_text")).sendKeys("Yury Chistyakov");

        System.out.println("First test run");
    }
}
