package templates.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
       plugin = { "progress", "json:target/cucumber/all.json"
               ,"junit:target/surefire-reports/TEST-templates.cucumber.AllBDD.xml"
       },
        features = "classpath:cucumber"
)
public class AllBDD {
}