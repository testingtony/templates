package templates.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Settings;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OfficesPage extends LoadableComponent<OfficesPage>{
    private final static Logger logger = LoggerFactory.getLogger(OfficesPage.class);
    private WebDriver driver;
    private final Settings settings = new Settings();
    private final String wantedURL = settings.baseUrl + "offices.html";

    public OfficesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {
        driver.get(wantedURL);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertThat("Not on the home page: ", url, equalTo(wantedURL));
    }

    @FindBy(className = "office")
    private List<WebElement> offices;

    public int getCountOfOffices() {
        return offices.size();
    }
}

