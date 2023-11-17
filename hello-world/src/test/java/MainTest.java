import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {

    @Test
    public void getMessageCorrect_3p() {
        assertEquals("Hello World!", Main.getMessage());
    }

    @Test
    public void testAlwaysTrue_0p() {
        assertTrue(true);
    }

    @Test
    public void testThrowException() {
        int x = 5 / 0;
    }
}
