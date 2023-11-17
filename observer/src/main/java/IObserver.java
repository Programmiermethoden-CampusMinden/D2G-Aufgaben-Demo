/** IObserver-Interface. */
public interface IObserver {
    /**
     * Das Verhalten wenn ein WarenTyp sich beim zu beobachten Objekt geändert hat.
     *
     * @param typ der typ der Ware welcher sich verändert hat
     */
    void update(WarenTyp typ);
}
