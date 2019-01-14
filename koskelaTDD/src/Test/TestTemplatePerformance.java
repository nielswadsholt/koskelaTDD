package Test;

import ai.brothersinarms.Template;
import com.sun.source.tree.AssertTree;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestTemplatePerformance {

    private Template template;

    @Before
    public void setUp() throws Exception {
        // should be 100 words and 20 variables
        template = new Template("${one}, ${two}, ${three}");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
    }

    @Test
    public void templateWith100WordsAnd20Variables() throws Exception {
        long expected = 200L;
        long time = System.currentTimeMillis();
        template.evaluate();
        time = System.currentTimeMillis() - time;

        assertTrue("Rendering the template took " + time
                + "ms while the target was " + expected + "ms",
                time <= expected);
    }
}
