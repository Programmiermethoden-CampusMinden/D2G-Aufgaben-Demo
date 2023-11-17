import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(Enclosed.class)
public class EinzelhandelTest {
    @RunWith(MockitoJUnitRunner.class)
    public static class BestellenTests {
        AbstrakterEinzelhandel einzelhandel;
        Grosshandel grosshandel;

        /**
         * Für alle tests wird ein Grosshandel gemocked und eine gültige Instanz des Einzelhandels
         * erstellt.
         */
        @Before
        public void setUp() {
            grosshandel = Mockito.mock(Grosshandel.class);
            einzelhandel = new Einzelhandel(grosshandel);
        }

        /**
         * Das Bestellen ist gültig, wenn ein gültiger Auftrag übergeben wird. Wenn dies der Fall
         * ist, wird der Auftrag beim Einzelhandel hinterlegt.
         */
        @Test
        public void bestellenValid() {
            Auftrag auftrag = new Auftrag(WarenTyp.Batterien, 1);
            einzelhandel.bestellen(auftrag);
            assertTrue(einzelhandel.getAuftraege().contains(auftrag));
            assertEquals(1, einzelhandel.getAuftraege().size());
        }

        /**
         * Das Bestellen ist gültig, wenn ein gültiger Auftrag übergeben wird. Wenn dies der Fall
         * ist, wird der Auftrag beim Einzelhandel hinterlegt. Ein Auftrag kann mehr als einmal beim
         * Einzelhandel eingehen und dadurch mehrmals hinterlegt werden.
         */
        @Test
        public void bestellenAgain() {
            Auftrag auftrag = new Auftrag(WarenTyp.Batterien, 1);
            einzelhandel.bestellen(auftrag);
            einzelhandel.bestellen(auftrag);
            assertTrue(einzelhandel.getAuftraege().contains(auftrag));
            assertEquals(2, einzelhandel.getAuftraege().size());
        }

        /**
         * Es soll eine IllegalArgumentException geworfen werden sobald der Auftrag nicht gültig
         * ist.
         */
        @Test(expected = IllegalArgumentException.class)
        public void bestellenNull() {
            einzelhandel.bestellen(null);
        }
    }

    @RunWith(MockitoJUnitRunner.class)
    public static class EmpfangenTests {
        /** Auftrag gültig Auftrag nicht in der liste */
        AbstrakterEinzelhandel einzelhandel;

        AbstrakterGrosshandel grosshandel;
        Auftrag auftrag1;

        /**
         * Für alle tests wird ein Grosshandel gemocked und eine gültige Instanz des Einzelhandels
         * erstellt. Außerdem wird ein gültiger Auftrag angelegt und dieser wird bereits beim
         * Einzelhandel hinterlegt.
         */
        @Before
        public void setUp() {
            grosshandel = Mockito.mock(Grosshandel.class);
            einzelhandel = new Einzelhandel(grosshandel);
            auftrag1 = new Auftrag(WarenTyp.Batterien, 1);
            einzelhandel.bestellen(auftrag1);

            Mockito.clearInvocations(grosshandel);
        }

        /**
         * Es wird getestet, ob beim erhalten eines im Einzelhandel hinterlegten Auftrages dieser
         * beim empfangen abgearbeitet wird.
         */
        @Test
        public void empfangenValid() {
            einzelhandel.empfangen(auftrag1);
            assertEquals(0, einzelhandel.getAuftraege().size());
            assertEquals(
                    auftrag1.getAnzahl(),
                    (int) einzelhandel.lager.getOrDefault(auftrag1.getWarenTyp(), -1));
        }

        /**
         * Sollte ein Auftrag empfangen werden, welcher nicht in der Auftragsliste enthalten ist,
         * somit soll eine IllegalArgumentException geworfen werden und keine Änderungen an dem
         * Lager oder der Auftragsliste stattfinden.
         */
        @Test
        public void empfangenNichtInAuftragsliste() {
            Auftrag auftrag2 = new Auftrag(WarenTyp.Bretter, 1);
            assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        einzelhandel.empfangen(auftrag2);
                    });
            assertEquals(1, einzelhandel.getAuftraege().size());
            assertEquals(-1, (int) einzelhandel.lager.getOrDefault(auftrag1.getWarenTyp(), -1));
            assertEquals(-1, (int) einzelhandel.lager.getOrDefault(auftrag2.getWarenTyp(), -1));
        }

        /**
         * Sollte kein gültiger Auftrag empfangen werden, somit soll eine IllegalArgumentException
         * geworfen werden und keine Änderungen an dem Lager oder der Auftragsliste stattfinden.
         */
        @Test(expected = IllegalArgumentException.class)
        public void empfangenNull() {
            einzelhandel.empfangen(null);
        }
    }

    @RunWith(MockitoJUnitRunner.class)
    public static class UpdateTests {
        Einzelhandel einzelhandel;

        Grosshandel grosshandel;

        /**
         * Für alle tests wird ein Grosshandel gemocked und eine gültige Instanz des Einzelhandels
         * erstellt.
         */
        @Before
        public void setUp() {
            grosshandel = Mockito.mock(Grosshandel.class);
            einzelhandel = new Einzelhandel(grosshandel);
            Mockito.clearInvocations(grosshandel);
        }

        /**
         * Wenn der Warentyp mit der von einem Auftrag in der Auftragsliste übereinstimmt, dann soll
         * ein Bestellen mit allen möglichen Aufträgen des Warentyps ausgeführt werden.
         */
        @Test
        public void updateValid() {
            Auftrag auftrag = new Auftrag(WarenTyp.Batterien, 1);
            einzelhandel.bestellen(auftrag);
            einzelhandel.update(auftrag.getWarenTyp());
            Mockito.verify(grosshandel).bestellen(einzelhandel, auftrag);
        }

        /**
         * Wenn der Warentyp nicht mit der von einem Auftrag in der Auftragsliste übereinstimmt,
         * dann sollen keine Interaktionen zum Grosshandel existieren.
         */
        @Test
        public void updateNotRequested() {
            einzelhandel.update(WarenTyp.Bretter);
            Mockito.verifyNoInteractions(grosshandel);
        }

        /**
         * Wenn der Warentyp nicht gültig ist, soll eine IllegalArgumentException geworfen werden
         * und keine Interaktionen zum Grosshandel existieren.
         */
        @Test
        public void updateNull() {
            assertThrows(IllegalArgumentException.class, () -> einzelhandel.update(null));
            Mockito.verifyNoInteractions(grosshandel);
        }
    }
}
