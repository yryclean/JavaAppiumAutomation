package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {
    private static final String
    SAVED_LISTING = "xpath://*[@content-desc='Saved']";
    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
        public void clickSavedList()
        {
            this.waitForElementAndClick(
                    (SAVED_LISTING),
                    "Cannot find Saved button",
                    10
            );
        }
}
