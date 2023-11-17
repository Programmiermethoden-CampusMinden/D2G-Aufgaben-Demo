import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AuftragTest {
    /**
     * Das erstellen eines Auftrags muss einen gültigen Warentypen enthalten und die Anzahl muss
     * größer als 0 sein.
     */
    @Test
    public void createValid() {
        Auftrag a = new Auftrag(WarenTyp.Batterien, 1);
        assertEquals(1, a.getAnzahl());
        assertEquals(WarenTyp.Batterien, a.getWarenTyp());
    }

    /**
     * Das Erstellen eines Auftrags ohne gültigen Warentyp soll eine `IllegalArgumentException`
     * werfen.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createInvalidTyp() {
        Auftrag a = new Auftrag(null, 1);
    }

    /**
     * Das Erstellen eines Auftrags mit einer Menge von weniger als 1 soll eine
     * `IllegalArgumentException` werfen.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createInvalidCount() {
        Auftrag a = new Auftrag(WarenTyp.Batterien, 0);
    }
}
