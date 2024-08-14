package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject {
    protected static String
           SAVED_LISTING;
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
