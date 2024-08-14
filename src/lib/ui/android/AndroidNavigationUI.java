package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI
{
    static {
        SAVED_LISTING = "xpath://*[@content-desc='Saved']";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
