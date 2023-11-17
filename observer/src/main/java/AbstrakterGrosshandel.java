import java.util.HashMap;

/** Abstrakte Klasse des Grosshandels. */
public abstract class AbstrakterGrosshandel implements IObserveable {
    protected HashMap<WarenTyp, Integer> lager;

    /** Erstellt eine Hashmap und initialisiert alle Warentypen mit dem Wert 0. */
    public AbstrakterGrosshandel() {
        lager = new HashMap<>();
        for (WarenTyp typ : WarenTyp.values()) {
            lager.put(typ, 0);
        }
    }

    /**
     * Ein Kunde soll in der Lage sein beim Grosshandel ware zu bestellen.
     *
     * @param kunde welcher kunde die Bestellung abgibt
     * @param auftrag ein auftrag wo der warentyp und die Menge definiert ist
     * @return ein Wahrheitswert, wenn der auftrag direkt abschliessbar ist true sonst false.
     */
    public abstract boolean bestellen(AbstrakterEinzelhandel kunde, Auftrag auftrag);

    /** Der Grosshandel bekommt immer Ware, von der am wenigsten aktuell verfügbar ist. */
    public abstract void loop();
}
