package templates.selenium.tests;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import templates.selenium.pages.ProfilePage;
import templates.selenium.utils.ScreenShot;
import templates.selenium.utils.WebDriverRule;
import utils.TestNameLogger;
import utils.Settings;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ITProfilePage {
    private static final Logger logger = LoggerFactory.getLogger(ITProfilePage.class);
    private Settings settings = new Settings();
    private ProfilePage profilePage;

    @ClassRule
    public static WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public ScreenShot screenShot = ScreenShot.onFailure(webDriverRule);

    @Rule
    public TestNameLogger testNameLogger = new TestNameLogger();

    @Before
    public void setUp() {
        profilePage = new ProfilePage(webDriverRule.getDriver()).get();
    }

    @Test
    public void welcomesUser() {
        String message = profilePage.getWelcomeMessage();

        assertThat(message, equalTo("Welcome " + settings.validUser));
    }
}
