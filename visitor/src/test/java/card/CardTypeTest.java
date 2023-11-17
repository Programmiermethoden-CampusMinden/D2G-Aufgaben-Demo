package card;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CardTypeTest {

    /** Tests the order of the CardType symbols. */
    @Test
    public void testOrderOfSymbols() {
        assertEquals(0, CardType.CAR.ordinal());
        assertEquals(1, CardType.AIRPLANE.ordinal());
        assertEquals(2, CardType.MOTORCYCLE.ordinal());
        assertEquals(3, CardType.BOAT.ordinal());
    }
}
