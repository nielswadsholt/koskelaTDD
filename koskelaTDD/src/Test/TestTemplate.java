package Test;

import ai.brothersinarms.Template;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestTemplate {

    private Template template;

    @Before
    public void setUp() throws Exception {
        template = new Template("${one}, ${two}, ${three}");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
    }

    @Test
    public void multipleVariables() throws Exception {
        assertTemplateEvaluatesTo("1, 2, 3");
    }

    @Test
    public void unknownVariablesAreIgnored() throws Exception {
        template.set("doesnotexist", "whatever");
        assertTemplateEvaluatesTo("1, 2, 3");
    }

    @Test
    public void missingValueRaisesException() throws Exception {
        try {
            new Template("${boo}").evaluate();
            fail("evaluate() should throw an exception if "
            + "a variable was left without a value!");
        } catch (MissingValueException expected) {
            assertEquals("No value for ${boo}", expected.getMessage());
        }
    }

    // Not implemented properly yet:
    /*@Test
    public void variablesGetProcessedJustOnce() throws Exception {
        template.set("one", "@{one}");
        template.set("two", "@{three}");
        template.set("three", "@{two}");
        assertTemplateEvaluatesTo("@{one}, @{three}, @{two}");
    }*/

    private void assertTemplateEvaluatesTo(String expected) {
        assertEquals(expected, template.evaluate());
    }
}
