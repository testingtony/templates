package templates.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Tree {
    private WebElement root;
    public Tree(WebElement root) {
        this.root = root;
    }

    public boolean isFolder() {
        return ! isTest();
    }

    public boolean isEmptyFolder() {
        List<WebElement> children = root.findElements(By.tagName("div"));
        return isFolder() && children.isEmpty();
    }

    public boolean isTest() {
        String[] classes = root.getAttribute("class").split("\\s+");
        for(String cls: classes) {
            if (cls.equals("test")) {
                return true;
            }
        }
        return false;
    }

    public Tree folder(String title) {
        WebElement child = root.findElement(By.xpath("./div/span[text()='" + title + "']/parent::div"));
        return new Tree(child);
    }

    public Tree folder(String ... titles) {
        Tree child = this;
        for(String title: titles) {
            child = child.folder(title);
        }
        return child;
    }

    public Tree folder (HasBy title) {
        WebElement child = root.findElement(title.getBy());
        return new Tree(child);
    }

    public Tree folder(HasBy ... titles) {
        Tree child = this;
        for(HasBy title: titles) {
            child = child.folder(title);
        }
        return child;
    }


    public String getTitle() {
        return root.findElement(By.tagName("span")).getText();
    }

    public boolean exists() {
        return root.isDisplayed();
    }


}
