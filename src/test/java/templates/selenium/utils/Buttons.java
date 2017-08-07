package templates.selenium.utils;


import org.openqa.selenium.By;


public enum Buttons implements HasBy {
    OK("//span[@text='ok']"),
    STOP("//span[@text='stop']"),
    FIND(sort.TEXT, "find"),
    FOLDER1(sort.FOLDER, "Folder 1"),
    FOLDER1_1("Folder 1", "Folder 1.1"),
    FOLDER1_2("Folder 1", "Folder 1.2"),
    FOLDER1_2_1("Folder 1", "Folder 1.2", "Folder 1.2.1"),
    ;

    By locator;
    enum sort {XPATH, CSS, TEXT, FOLDER};
    Buttons(String xpath) {
        this(sort.XPATH, xpath);
    }

    Buttons(sort by, String locator) {
        switch (by) {
            case XPATH:
                this.locator = By.xpath(locator);
                break;
            case CSS:
                this.locator = By.cssSelector(locator);
                break;
            case TEXT:
                this.locator = By.xpath("//div[text()='" + locator + "']");
                break;
            case FOLDER:
                this.locator = By.xpath("./div/span[text()='" + locator + "']/parent::div");
                break;
        }
    }

    Buttons(String ... locators) {
        StringBuilder sb = new StringBuilder(".");
        for(String locator: locators) {
            sb.append("/div/span[text()='" + locator + "']/parent::div");
        }
        this.locator = By.xpath(sb.toString());
    }
    @Override
    public By getBy() {
        return locator;
    }
}
