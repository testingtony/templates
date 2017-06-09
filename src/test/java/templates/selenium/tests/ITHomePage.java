package templates.selenium.tests;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestNameLogger;
import templates.selenium.utils.ScreenShot;
import templates.selenium.utils.WebDriverRule;
import templates.selenium.pages.HomePage;
import utils.Settings;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ITHomePage {
    private static final Logger logger = LoggerFactory.getLogger(ITHomePage.class);
    private Settings settings = new Settings();
    private HomePage homePage;

    @ClassRule
    public static WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public ScreenShot screenShot = ScreenShot.onFailure(webDriverRule);

    @Rule
    public TestNameLogger testNameLogger = new TestNameLogger();

    @Before
    public void setUp() {
        homePage = new HomePage(webDriverRule.getDriver()).get();
    }

    @Test
    public void isHeaderVisible() {
        assertThat(homePage.isHeaderDisplayed(), is(true));
    }

    @Test
    public void aGoodAgeIsValid() {
        homePage.fillForm("fname", "lname", "25");

        assertThat(homePage.isFormValid(), is(true));
    }

    @Test
    public void tooYoungIsRejected() {
        homePage.fillForm("fname", "lname", "17");

        assertThat(homePage.isFormValid(), is(false));
    }

    @Test
    public void tooOldIsRejected() {
        homePage.fillForm("fname", "lname", "33");

        assertThat(homePage.isFormValid(), is(false));
    }

    @Test
    public void isNotLoggedInByDefault() {
        assertThat(homePage.isLoggedIn(), is(false));
    }

    @Test
    public void hasProfileLink() {
        assertThat(homePage.hasProfileLink(), is(true));
    }

    @Test
    public void hasAccountLink() {
        assertThat(homePage.hasAccountLink(), is(true));
    }
}
