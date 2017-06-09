package templates.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import utils.Settings;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProfilePage extends LoadableComponent<ProfilePage>{
    private WebDriver driver;

    private Settings settings = new Settings();

    public final String wantedURL = settings.baseUrl + "profile.php";

    public ProfilePage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @Override
    protected void load() {
        LoginPage loginPage = new LoginPage(driver).get();
        loginPage.loginWith(settings.validUser, settings.validPassword);
        driver.get(wantedURL);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertThat("Not on the profile page: ", url, equalTo(wantedURL));
    }

    @FindBy(css="body h1:first-of-type")
    private WebElement welcomeHeader;

    public String getWelcomeMessage() {
        return welcomeHeader.getText();
    }
}
