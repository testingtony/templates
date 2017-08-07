package templates.selenium.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import templates.selenium.utils.Tree;
import utils.Settings;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TreePage extends LoadableComponent<TreePage> {
    private WebDriver driver;

    private Settings settings = new Settings();

    public final String wantedURL = settings.baseUrl + "tree.html";

    public TreePage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath="//div[@id='root']")
    WebElement treeRoot;

    @Override
    protected void load() {
        driver.get(wantedURL);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertThat("Not on the profile page: ", url, equalTo(wantedURL));
    }

    public boolean hasRoot() {
        return treeRoot.isDisplayed();
    }

    public Tree getTree() {
        return new Tree(treeRoot);
    }
}
