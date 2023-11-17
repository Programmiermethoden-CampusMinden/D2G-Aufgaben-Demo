import java.util.HashMap;
import java.util.List;

/** Abstrakte Klasse des Einzelhandels. */
public abstract class AbstrakterEinzelhandel implements IObserver {
    protected HashMap<WarenTyp, Integer> lager;

    /** erstellt eine Leere Hashmap welche als representation eines Lager genutzt wird. */
    public AbstrakterEinzelhandel() {
        lager = new HashMap<>();
    }

    /**
     * Kunde bestellt Ware: Speichere den Auftrag ab und versuche später in <code>loop()</code>, den
     * Auftrag beim Großhandel zu bestellen.
     *
     * @param auftrag der Auftrag, der aufgenommen werden soll.
     */
    public abstract void bestellen(Auftrag auftrag);

    /**
     * Empfange Ware vom Großhandel, füge die Ware dem Lager hinzu und entferne den offenen Auftrag.
     *
     * @param auftrag der Auftrag, der abgearbeitet werden soll.
     */
    public abstract void empfangen(Auftrag auftrag);

    /**
     * Gebe eine Liste aller offenen Aufträge zurück, die der Einzelhändler noch nicht abschliessen
     * konnte.
     *
     * @return eine Liste aller offenen Aufträge des Einzelhändlers
     */
    public abstract List<Auftrag> getAuftraege();
}
