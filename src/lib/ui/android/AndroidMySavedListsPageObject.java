package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MySavedListsPageObject;

public class AndroidMySavedListsPageObject extends MySavedListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
    }
    public AndroidMySavedListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
