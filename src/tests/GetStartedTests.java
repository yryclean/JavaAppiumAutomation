package tests;
import lib.CoreTestCase;
import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTests extends CoreTestCase
{
    @Test
    public void testPassThroughWelcome()
    {
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
        WelcomePageObject.waitForLearnMoreLink();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForNewWaysToExplore();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitAddLanguagesLink();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitLearnMoreAboutCollectedDataLink();
        WelcomePageObject.clickGetStartedButton();
    }
}
