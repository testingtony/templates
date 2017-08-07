package templates.selenium.utils;

import org.openqa.selenium.By;

import javax.xml.xpath.XPath;

import static templates.selenium.utils.ByEnum.sort.XPATH;

public enum ByEnum implements HasBy {
    NONE("none");
    enum sort {XPATH, ID, CSS};

    private By locator;

    @Override
    public By getBy() {
        return locator;
    }

    ByEnum(String xpath) {
        this(xpath, XPATH);
    }

    ByEnum(String string, sort by) {
        switch(by) {
            case XPATH:
                locator = By.xpath(string);
                break;
            case ID:
                locator = By.id(string);
                break;
            case CSS:
                locator = By.cssSelector(string);
                break;
        }
    }

}
