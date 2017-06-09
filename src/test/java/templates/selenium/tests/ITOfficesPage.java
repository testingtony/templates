package templates.selenium.tests;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import templates.selenium.pages.OfficesPage;
import templates.selenium.utils.ScreenShot;
import templates.selenium.utils.WebDriverRule;
import utils.Settings;
import utils.TestNameLogger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ITOfficesPage {
    private static final Logger logger = LoggerFactory.getLogger(ITOfficesPage.class);
    private Settings settings = new Settings();
    private OfficesPage officesPage;

    @ClassRule
    public static WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public ScreenShot screenShot = ScreenShot.onFailure(webDriverRule);

    @Rule
    public TestNameLogger testNameLogger = new TestNameLogger();

    @Before
    public void setUp() {
        officesPage = new OfficesPage(webDriverRule.getDriver()).get();
    }

    @Test
    public void allOfficesAreDisplayed() {
        int countOfOffices = officesPage.getCountOfOffices();

        assertThat(countOfOffices, equalTo(3));

    }
}
