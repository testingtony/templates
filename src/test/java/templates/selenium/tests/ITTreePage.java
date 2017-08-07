package templates.selenium.tests;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import templates.selenium.pages.TreePage;
import templates.selenium.utils.Tree;
import templates.selenium.utils.WebDriverRule;
import utils.Settings;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static templates.selenium.utils.Buttons.*;


public class ITTreePage {
    private static final Logger logger = LoggerFactory.getLogger(ITProfilePage.class);
    private Settings settings = new Settings();
    private TreePage treePage;

    @ClassRule
    public static WebDriverRule webDriverRule = new WebDriverRule();


    @Before
    public void setUp() {
        treePage = new TreePage(webDriverRule.getDriver()).get();
    }

    @Test
    public void has_root() {
        assertThat(treePage.hasRoot(), is(true));
    }

    @Test
    public void root_has_title_Root() {
        Tree tree = treePage.getTree();

        assertThat(tree.getTitle(), equalTo("Root"));
    }

    @Test
    public void root_has_Folder1_node() {
        Tree tree = treePage.getTree();
        Tree subFolder = tree.folder(FOLDER1);

        assertThat(subFolder.getTitle(), equalTo("Folder 1"));
    }

    @Test
    public void folder1_1_is_empty() {
        Tree folder1 = treePage.getTree().folder(FOLDER1_1);

        assertThat(folder1.isFolder(), is(true));
        assertThat(folder1.isTest(), is(false));
        assertThat(folder1.isEmptyFolder(), is(true));
    }

    @Test
    public void folder12_has_children() {
        Tree folder12 = treePage.getTree().folder(FOLDER1_2);

        assertThat(folder12.isFolder(), is(true));
        assertThat(folder12.isTest(), is(false));
        assertThat(folder12.isEmptyFolder(), is(false));
    }

    @Test
    public void folder_1_2_1_has_children() {
        Tree folder12 = treePage.getTree().folder("Folder 1", "Folder 1.2", "Folder 1.2.1");

        assertThat(folder12.isFolder(), is(true));
        assertThat(folder12.isTest(), is(false));
        assertThat(folder12.isEmptyFolder(), is(false));

    }

}
