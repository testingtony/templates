package templates.selenium.utils;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestNameLogger;

import java.io.File;

public abstract class ScreenShot implements TestRule {
    protected final static Logger logger = LoggerFactory.getLogger(ScreenShot.class);

    private WebDriverRule webDriverRule;
    private Description description;
    private int count;

    public ScreenShot(WebDriverRule webDriverRule) {
        this.webDriverRule = webDriverRule;
    }

    public void setDescription(Description description) {
        this.description = description;
        count = 0;
    }

    public void snap() {
        String testName = TestNameLogger.methodNameToTestName(description.getMethodName(), "_");
        take(webDriverRule.getDriver(), String.format("%s_%02d", testName, ++count));
    }

    public static ScreenShot onFailure(final WebDriverRule webDriverRule) {
        return new ScreenShot(webDriverRule) {
            @Override
            public Statement apply(Statement statement, Description description) {
                setDescription(description);
                return new Statement() {
                    @Override
                    public void evaluate() throws Throwable {
                        try {
                            statement.evaluate();
                        } catch (Throwable e) {
                            WebDriver webDriver = webDriverRule.getDriver();
                            logger.debug("webdriver is " + webDriver);
                            if (webDriver instanceof TakesScreenshot) {
                                take(webDriver, description.getMethodName());
                            }
                            throw e;
                        }
                    }
                };
            }
        };
    }

    public static ScreenShot atEnd(final WebDriverRule webDriverRule) {
        return new ScreenShot(webDriverRule) {
            @Override
            public Statement apply(Statement statement, Description description) {
                setDescription(description);
                return new Statement() {
                    @Override
                    public void evaluate() throws Throwable {
                        try {
                            statement.evaluate();
                        } finally {
                            WebDriver webDriver = webDriverRule.getDriver();
                            if (webDriver instanceof TakesScreenshot) {
                                take(webDriver, description.getMethodName());
                            }
                        }
                    }
                };
            }
        };
    }


    public static void take(WebDriver driver, String id) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dir = new File("target/screenshots");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File result = File.createTempFile("screenshot_" + id + "_", ".png", dir);
            FileUtils.copyFile(scrFile, result);
            System.err.println("[[ATTACHMENT|" + result.getAbsolutePath()+"]]");
        } catch (Exception e) {
            logger.error("Error taking screenshot", e);
        }
    }

}
