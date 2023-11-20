import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** Example of a CLI test */
public class CliTest {

    private ByteArrayOutputStream outStream;

    @Before
    public void before() {
        // Set stdout to custom out stream
        outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
    }

    @Test
    public void testMessagePrintCorrect() {
        Main.main(new String[] {});
        assertEquals("Hello World!\n", outStream.toString());
    }

    @After
    public void after() {
        // Reset out to default stdout
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }
}
