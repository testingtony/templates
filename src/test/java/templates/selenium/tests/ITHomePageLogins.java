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
import templates.selenium.pages.LoginPage;
import utils.Settings;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static templates.selenium.utils.IsLoaded.isLoaded;

/*
Tests running in the same browser, must not have state
 */
public class ITHomePageLogins {
    private static final Logger logger = LoggerFactory.getLogger(ITHomePageLogins.class);
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
    public void validLoginIsLoggedIn() {
        logOutIfLoggedIn();
        LoginPage loginPage = homePage.clickLogin();

        loginPage.loginWith(settings.validUser, settings.validPassword);

        assertThat(homePage.isLoggedIn(), is(true));
    }

    @Test
    public void invalidLoginIsNotLoggedIn() {
        logOutIfLoggedIn();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.loginWith(settings.invalidUser, settings.invalidPassword);
        homePage.get();
        assertThat(homePage.isLoggedIn(), is(false));
    }

    @Test
    public void loggedInWithCorrectName() {
        logOutIfLoggedIn();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.loginWith(settings.validUser, settings.validPassword);

        String loginName = homePage.getLoggedInAs();

        assertThat(loginName, equalTo(settings.validUser) );
    }

    @Test
    public void invalidLoginStaysOnLoginPage() {
        logOutIfLoggedIn();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.loginWith(settings.invalidUser, settings.invalidPassword);

        assertThat(loginPage, isLoaded());
    }

    @Test
    public void loggedInReturnsToHomePage() {
        logOutIfLoggedIn();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.loginWith(settings.validUser, settings.validPassword);

        assertThat(homePage, isLoaded());
    }

    private void logOutIfLoggedIn() {
        if (homePage.isLoggedIn()) {
            homePage.clickLogout();
        }
    }
}
