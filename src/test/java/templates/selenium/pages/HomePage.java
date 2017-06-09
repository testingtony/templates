package templates.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import templates.selenium.utils.Waits;
import utils.Settings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static templates.selenium.utils.Waits.pauseToLoad;

public class HomePage extends LoadableComponent<HomePage>{
    private final static Logger logger = LoggerFactory.getLogger(HomePage.class);
    private WebDriver driver;
    private final Settings settings = new Settings();
    private final String wantedURL = settings.baseUrl;

    public HomePage(WebDriver driver) {
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
        assertThat("Not on the home page: ", url, anyOf(equalTo(wantedURL), equalTo(wantedURL + "index.php")));
    }

    @FindBy(tagName = "form")
    private WebElement form;

    @FindBy(css="input[name='firstname']")
    private WebElement firstName;

    @FindBy(css="input[name='lastname']")
    private WebElement lastName;

    @FindBy(css="input[name='age']")
    private WebElement age;

    @FindBy(css="input[type='submit']")
    private WebElement submit;

    @FindBy(id="login")
    private WebElement logIn;

    @FindBy(css="a[href='profile.php']")
    private WebElement profileLink;

    @FindBy(css="a[href='accounts.php']")
    private WebElement accountsLink;

    @FindBy(xpath="//a[@id='login']/..")
    private WebElement logInParagraph;

    private By headerLocator = By.xpath("//h1[@class='heading' and text()='Header']");

    public boolean isHeaderDisplayed() {
        WebElement header = driver.findElement(headerLocator);
        return header.isDisplayed();
    }

    public boolean isFormValid() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return (Boolean)js.executeScript("return arguments[0].checkValidity();", form);
    }

    public void fillForm(String firstName, String lastName, String age) {
        Waits.waitUntilFound(this.firstName, this.lastName, this.age);

        this.firstName.clear();
        this.firstName.sendKeys(firstName);
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
        this.age.clear();
        this.age.sendKeys(age);
        this.firstName.click();
    }

    public boolean isLoggedIn() {
        String classes =  logIn.getAttribute("class");
        logger.debug("Classes are '" + classes + "'");
        if (classes == null) {
            return false;
        }
        for(String className: classes.split("\\s+")) {
            logger.debug("class '" + className + "'");
            if ("loggedIn".equalsIgnoreCase(className)) {
                return true;
            }
        }
        return false;
    }

    public LoginPage clickLogin() {
        logIn.click();
        return new LoginPage(driver);
    }

    public HomePage clickLogout() {
        logIn.click();
        pauseToLoad();
        return new HomePage(driver);
    }

    public String getLoggedInAs() {
        Pattern p = Pattern.compile("You are logged in as (.*) Logout");

        String text = logInParagraph.getText();
        logger.debug("Text is '" + text + "'");
        Matcher m = p.matcher(text);
        m.find();
        return m.group(1);
    }

    public boolean hasProfileLink() {
        return Waits.waitUntilFound(profileLink);
    }

    public void clickProfileLink() {
        profileLink.click();
        pauseToLoad();
    }

    public boolean hasAccountLink() {
        return Waits.waitUntilFound(accountsLink);
    }
}

