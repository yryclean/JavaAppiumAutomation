package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI
{
    static {
        SAVED_LISTING = "id:tabbar-save";
    }
    public iOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
