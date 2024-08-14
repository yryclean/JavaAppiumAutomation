package lib.ui.factories;
import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MySavedListsPageObject;
import lib.ui.android.AndroidMySavedListsPageObject;
import lib.ui.ios.iOSMySavedListsPageObject;

public class MySavedListsPageObjectFactory {

    public static MySavedListsPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()){
            return new AndroidMySavedListsPageObject(driver);
        } else {
            return new iOSMySavedListsPageObject(driver);
        }
    }
}
