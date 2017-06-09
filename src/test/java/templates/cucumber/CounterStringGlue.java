package templates.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import templates.CounterString;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CounterStringGlue {
    CounterString counterString;
    String resultString;

    @Given("^I have a CounterString$")
    public void iHaveADefaultCounterstring() {
        counterString = new CounterString();
    }

    @Given("^I have a CounterString with separator '(.)' and terminator '(.)'$")
    public void iHaveAnExplicitCounterstring(char separator, char terminator) {
        counterString = new CounterString(separator, terminator);
    }

    @When("^I generate a ([0-9]+) character string$")
    public void generateString(Integer length) {
        resultString = counterString.generate(length);
    }

    @Then("^I get \"(.*)\"$")
    public void theStringResult(String expected) {
        assertThat(resultString, equalTo(expected));
    }

}
