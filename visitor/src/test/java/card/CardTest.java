package card;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CardTest {

    /**
     * Tests the consturctor and compareTo method of Card.
     *
     * <p>Same object/reference should be equal.
     */
    @Test
    public void testCompareIdenticalObjectIds() {
        Card c1 = new Card(CardType.CAR, "1", 2, 3, 4, 5);

        // same object/reference should be equal
        assertEquals(0, c1.compareTo(c1));
    }

    /**
     * Tests the consturctor and compareTo method of Card.
     *
     * <p>Object with identical attributes values should be equal.
     */
    @Test
    public void testCompareIdenticalValues() {
        Card c1 = new Card(CardType.CAR, "1", 2, 3, 4, 5);
        Card c2 = new Card(CardType.CAR, "1", 2, 3, 4, 5);

        // object with identical attributes values should be equal
        assertEquals(0, c1.compareTo(c2));
        assertEquals(0, c2.compareTo(c1));
    }

    /**
     * Tests the consturctor and compareTo method of Card.
     *
     * <p>Compare attribute "price" first: c1 < c2.
     */
    @Test
    public void testCompareDifferentPrices() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 5);
        Card c2 = new Card(CardType.CAR, "B", 2, 3, 4, 6);

        // compare attribute "price" first: c1 < c2
        assertTrue(c1.compareTo(c2) < 0);
        assertTrue(c2.compareTo(c1) > 0);
    }

    /**
     * Tests the consturctor and compareTo method of Card.
     *
     * <p>Price being identical, compare attribute "maxDistance" second: c1 < c2.
     */
    @Test
    public void testCompareDifferentMaxDistances() {
        Card c1 = new Card(CardType.CAR, "A", 22, 3, 44, 55);
        Card c2 = new Card(CardType.CAR, "B", 2, 4, 4, 55);

        // price being identical, compare attribute "maxDistance" second: c1 < c2
        assertTrue(c1.compareTo(c2) < 0);
        assertTrue(c2.compareTo(c1) > 0);
    }

    /**
     * Tests the consturctor and compareTo method of Card.
     *
     * <p>Price and maxDistance being identical, compare attribute "fuelConsumption" third: c1 < c2.
     */
    @Test
    public void testCompareDifferentFuelConsumption() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 4, 55);
        Card c2 = new Card(CardType.CAR, "B", 2, 33, 5, 55);

        // price and maxDistance being identical, compare attribute "fuelConsumption" third: c1 < c2
        assertTrue(c1.compareTo(c2) < 0);
        assertTrue(c2.compareTo(c1) > 0);
    }

    /**
     * Tests the consturctor and compareTo method of Card.
     *
     * <p>Price, maxDistance and fuelConsumption being identical, compare attribute "weight" third:
     * c1 < c2.
     */
    @Test
    public void testCompareDifferentWeights() {
        Card c1 = new Card(CardType.CAR, "A", 2, 33, 44, 55);
        Card c2 = new Card(CardType.CAR, "B", 3, 33, 44, 55);

        // price, maxDistance and fuelConsumption being identical, compare attribute "weight" third:
        // c1 < c2
        assertTrue(c1.compareTo(c2) < 0);
        assertTrue(c2.compareTo(c1) > 0);
    }

    /**
     * Tests the consturctor and compareTo method of Card.
     *
     * <p>Price, maxDistance, fuelConsumption and weight being identical, compare attribute
     * "cardTyp" fourth: c1 < c2.
     */
    @Test
    public void testCompareDifferentTypes() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 55);
        Card c2 = new Card(CardType.BOAT, "B", 22, 33, 44, 55);

        // price, maxDistance, fuelConsumption and weight being identical, compare attribute
        // "cardTyp" fourth: c1 < c2
        assertTrue(c1.compareTo(c2) < 0);
        assertTrue(c2.compareTo(c1) > 0);
    }

    /**
     * Tests the consturctor and compareTo method of Card.
     *
     * <p>Attribute "name" does not matter in comparison.
     */
    @Test
    public void testCompareName() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 55);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 55);

        // Attribute "name" does not matter in comparison
        assertEquals(0, c1.compareTo(c2));
        assertEquals(0, c2.compareTo(c1));
    }

    /** Tests the <code>toString()</code> method of Card. */
    @Test
    public void testToString() {
        Card c1 = new Card(CardType.CAR, "A B", 22, 33, 44, 55);

        assertEquals("CAR: 'A B'; ", c1.toString());
    }
}
