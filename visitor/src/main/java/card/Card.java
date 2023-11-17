package card;

import org.jetbrains.annotations.NotNull;

/**
 * A card for a Quartets game, representing different vehicles.
 *
 * @param cardType Type of the card
 * @param name Name of the vehicle on the card
 * @param weight Weight of the vehicle on the card
 * @param maxDistance Maximal travel distance of the vehicle on the card
 * @param fuelConsumption Fuel consumption (100km) of the vehicle on the card
 * @param price Price of the vehicle on the card
 */
public record Card(
        @NotNull CardType cardType,
        @NotNull String name,
        int weight,
        int maxDistance,
        int fuelConsumption,
        int price)
        implements Comparable<Card> {

    /**
     * Implement the Comparator interface.
     *
     * <p>Compares to cards according to the rules of Quartets game: Use price (unless equal), then
     * compare maxDistance (unless equal), then compare fuelConsumption (unless equal), then compare
     * weight (unless equal) and as last resort compare the cardType field (enum).
     *
     * @param o the object to be compared.
     * @return 0 if equal, values <0 if less, values >0 if greater
     */
    @Override
    public int compareTo(@NotNull Card o) {
        int compareValue = Integer.compare(price(), o.price());
        if (compareValue != 0) {
            return compareValue;
        }
        compareValue = Integer.compare(maxDistance(), o.maxDistance());
        if (compareValue != 0) {
            return compareValue;
        }
        compareValue = Integer.compare(fuelConsumption(), o.fuelConsumption());
        if (compareValue != 0) {
            return compareValue;
        }
        compareValue = Integer.compare(weight(), o.weight());
        if (compareValue != 0) {
            return compareValue;
        }
        return cardType().compareTo(o.cardType());
    }

    /** Returns a string built from cardType and name. */
    @Override
    public String toString() {
        return cardType() + ": '" + name() + "'; ";
    }
}
