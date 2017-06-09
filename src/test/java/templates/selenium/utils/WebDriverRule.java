package templates.selenium.utils;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverRule implements TestRule {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverRule.class);
    private WebDriver driver = null;
    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                driver = getWebDriver();
                try {
                    statement.evaluate();
                } catch (Throwable throwable) {
                    throw throwable;
                } finally {
                    driver.quit();
                    driver = null;
                }
            }
        };
    }

    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver getWebDriver() {
        final String wantedDriver = System.getProperty("browser.name", "chromedriver");
        final boolean isGrid = System.getProperties().containsKey("browser.grid");
        switch (wantedDriver) {
            case "chromedriver":
                if (isGrid) {
                    return getRCDriver(DesiredCapabilities.chrome());
                } else {
                    return new ChromeDriver();
                }
            case "firefox":
                if (isGrid) {
                    return getRCDriver(DesiredCapabilities.firefox());
                } else {
                    return new FirefoxDriver();
                }
            default:
                logger.error("Did not recognise system property 'browser.name' value '" + wantedDriver);
                throw new RuntimeException("Did not recognise system property 'browser.name'");
        }
    }

    private WebDriver getRCDriver(DesiredCapabilities desiredCapabilities) {
        String remoteHost = System.getProperty("grid.host", "localhost");
        String remotePort = System.getProperty("grid.port", "4444");
        String remoteUrl = System.getProperty("grid.url", "http://" + remoteHost + ":" + remotePort + "/wd/hub");
        try {
            return new RemoteWebDriver(new URL(remoteUrl), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
