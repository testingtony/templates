package templates.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import templates.selenium.utils.Waits;
import utils.Settings;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;


public class LoginPage extends LoadableComponent<LoginPage> {
    private WebDriver driver;
    private Settings settings = new Settings();
    private String wantedURL = settings.baseUrl + "login_page.php";

    private WebElement username;
    private WebElement password;
    private WebElement submit;

    public LoginPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @Override
    protected void load() {
        driver.get(wantedURL);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertThat("Not on the login page: ", url, startsWith(wantedURL));
    }

    @FindBy(className="error")
    private WebElement  errorSpan;

    public void loginWith(String username, String password) {
        Waits.waitUntilFound(this.username);
        this.username.clear();
        this.username.sendKeys(username);
        this.password.clear();
        this.password.sendKeys(password);
        this.submit.click();

        Waits.pause();
    }

    public String getErrorText() {
        return errorSpan.getText();
    }
}
