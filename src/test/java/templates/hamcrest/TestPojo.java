package templates.hamcrest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import templates.Pojo;
import utils.TestNameLogger;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestPojo {
    private final static int INITIAL_INT = 1;
    private final static String INITIAL_STRING = "One";

    private Pojo out;

    @Before
    public void setUp() {
        out = new Pojo(INITIAL_INT, INITIAL_STRING);
    }

    @Rule
    public TestNameLogger testNameLogger = new TestNameLogger();

    @Test
    public void compareSimple() {
        assertThat("The initial int was not stored/returned", out.getId(), is(INITIAL_INT));
    }

    @Test
    public void stringEquality() {
        assertThat(out.getName(), equalTo(INITIAL_STRING));
    }

    @Test
    public void assembleActionAssert() {
        final int ID = 12;
        out.setId(ID);

        int result = out.getId();

        assertThat(result, is(ID));

        assertThat(result, allOf(greaterThan(5), lessThan(20)));

    }

    @Test
    public void substrings() {
        out.setName("New Value");

        String result = out.getName();

        assertThat(result, containsString("Value"));

        Iterable<String> expected = Arrays.asList("New", "Value");
        assertThat(result, stringContainsInOrder(expected));

        assertThat(result, allOf(
                notNullValue(),
                containsString("New")
        ));
    }

    @Test
    public void stringCase() {
        String result = out.getLowerName();

        assertThat(result, equalToIgnoringCase(INITIAL_STRING.toLowerCase()));
    }

    @Test
    public void nullCheck() {
        String result = null;
        assertThat(result, nullValue());
        assertThat(result, isEmptyOrNullString());
    }

    @Test
    public void emptyCheck() {
        String result = "";

        assertThat(result, notNullValue());
        assertThat(result, isEmptyOrNullString());
        assertThat(result, isEmptyString());
    }


    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void expectException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(containsString("Name"));

        out.setName(null);
    }

    @Test
    public void trickyFloats() {
        double result = out.getDoubleId();

        assertThat(result, closeTo(INITIAL_INT * 2, 0.001));
    }

    @Test
    public void collections() {
        final int WANTEDSIZE = 7;
        List<Integer> result = out.fibonacci(WANTEDSIZE);
        List<Integer> expected = Arrays.asList(1, 1, 2, 3, 5, 8, 13);

        // [1, 1, 2, 3, 5, 8, 13]
        assertThat(result, hasSize(WANTEDSIZE));

        // any items
        assertThat(result, hasItem(5));
        assertThat(result, hasItems(13, 5));

        // all items in order & any order
        assertThat(result, contains(1, 1, 2, 3, 5, 8, 13));
        assertThat(result, containsInAnyOrder(1, 13, 1, 5, 2, 3, 8));
        assertThat(result, equalTo(expected));
        assertThat(result, contains(expected.toArray()));

        // check every item
        assertThat(result, everyItem(greaterThanOrEqualTo(1)));

    }
}
