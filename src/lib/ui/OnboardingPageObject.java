package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class OnboardingPageObject extends MainPageObject {

    private final static String
    SKIP_BUTTON = "//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']";

    public OnboardingPageObject(AppiumDriver driver)
    {
        super(driver);
    }
    public void skipOnboarding()
    {
        this.waitForElementAndClick(By.xpath(SKIP_BUTTON), "Can't find and click skip button", 5);
    }
}
