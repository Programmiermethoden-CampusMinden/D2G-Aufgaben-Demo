import java.util.List;

/** IObserveable wird von einer beobachtbaren Klasse implementiert. */
public interface IObserveable {
    /**
     * Benachrichtigt alle Observer.
     *
     * @param warentyp welcher Warentyp zuletzt geändert wurde.
     */
    void notifyObservers(WarenTyp warentyp);

    /**
     * Trägt den Observer ein damit er später benachrichtigt werden kann.
     *
     * @param observer der observer welcher benachrichtigt werden soll
     */
    void attach(IObserver observer);

    /**
     * Entfernt einen Observer damit dieser nicht mehr benachrichtigt wird.
     *
     * @param observer der observer welcher nicht mehr benachrichtigt werden soll
     */
    void detach(IObserver observer);

    /**
     * Eine Liste aller aktuellen observer.
     *
     * @return eine Liste aller observer die dem IObserveable beobachten
     */
    List<IObserver> getObserverList();
}
