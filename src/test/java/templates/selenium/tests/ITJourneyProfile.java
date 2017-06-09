package templates.selenium.tests;

import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import templates.selenium.pages.HomePage;
import templates.selenium.pages.LoginPage;
import templates.selenium.pages.ProfilePage;
import templates.selenium.utils.ScreenShot;
import templates.selenium.utils.WebDriverRule;
import utils.Settings;
import utils.TestNameLogger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static templates.selenium.utils.IsLoaded.isLoaded;
import static templates.selenium.utils.IsLoaded.isNotLoaded;


public class ITJourneyProfile {
    private static final Logger logger = LoggerFactory.getLogger(ITJourneyProfile.class);
    private Settings settings = new Settings();

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public ScreenShot screenShot = ScreenShot.onFailure(webDriverRule);

    @Rule
    public TestNameLogger testNameLogger = new TestNameLogger();

    @Test
    public void homePageToProfile() {
        logger.debug("Opening the home page");
        HomePage homePage = new HomePage(webDriverRule.getDriver()).get();
        screenShot.snap();
        assertThat("I want to start on the home page", homePage, isLoaded());

        logger.debug("Going to the profile page");
        homePage.clickProfileLink();

        screenShot.snap();

        LoginPage loginPage = new LoginPage(webDriverRule.getDriver());
        assertThat("I should now be on the login page", loginPage, isLoaded());

        loginPage.loginWith(settings.invalidUser, settings.invalidPassword);
        assertThat("I should still be on the login page", loginPage, isLoaded());
        assertThat(loginPage.getErrorText(), equalTo("Username or Password is invalid"));

        screenShot.snap();

        loginPage.loginWith(settings.validUser, settings.validPassword);
        assertThat("I should have left the login page",loginPage, isNotLoaded());

        screenShot.snap();

        ProfilePage profilePage = new ProfilePage(webDriverRule.getDriver());
        assertThat("I should now be on the profile page", profilePage, isLoaded());

        assertThat("I should be welcomed", profilePage.getWelcomeMessage(), endsWith(settings.validUser));
    }
}
