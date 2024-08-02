package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject
{
    private static final String
            STEP_LEARN_MORE_LINK = "Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "New ways to explore",
            STEP_ADD_LANGUAGES_LINK = "Add or edit preferred languages",
            STEP_LEARN_MORE_COLLECT_DATA_LINK = "Learn more about data collected",
            NEXT_BUTTON = "Next",
            GET_STARTED_BUTTON = "Get started";

    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }
    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(By.name(STEP_LEARN_MORE_LINK), "Can't find Learn more link", 30);
    }
    public void clickNextButton()
    {
        this.waitForElementAndClick(By.name(NEXT_BUTTON), "Can't find and click Next button", 10);
    }
    public void waitForNewWaysToExplore()
    {
        this.waitForElementPresent(By.id(STEP_NEW_WAYS_TO_EXPLORE_TEXT), "Can't find New ways to explore text", 10);
    }
    public void waitAddLanguagesLink()
    {
        this.waitForElementPresent(By.name(STEP_ADD_LANGUAGES_LINK), "Can't find Add or edit preferred languages link", 10);
    }
    public void waitLearnMoreAboutCollectedDataLink()
    {
        this.waitForElementPresent(By.name(STEP_LEARN_MORE_COLLECT_DATA_LINK), "Can't find Learn more about data collected link", 10);
    }
    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(By.name(GET_STARTED_BUTTON), "Can't find Get started button", 10);
    }
}

