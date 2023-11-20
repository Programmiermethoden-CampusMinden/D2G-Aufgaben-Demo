import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(Enclosed.class)
public class GrosshandelTest {
    @RunWith(MockitoJUnitRunner.class)
    public static class AttachTests {
        IObserveable observeable;
        IObserver observer;

        /**
         * Für alle Tests wird ein Grosshandel angelegt und ein Mock vom Einzelhandel vorbereitet.
         */
        @Before
        public void setUp() {
            observer = Mockito.mock(Einzelhandel.class);
            observeable = new Grosshandel();
        }

        /** Es soll möglich sein einen Observer an den Grosshandel anzuhängen. */
        @Test
        public void attachValid() {
            observeable.attach(observer);
            assertTrue(observeable.getObserverList().contains(observer));
        }

        /**
         * Es soll nicht möglich sein einen Observer an den Grosshandel zweimal anzuhängen. Deswegen
         * soll eine IllegalArgumentException geworfen werden und die Anzahl der observer sich nicht
         * verändern.
         */
        @Test
        public void attachValidTwice() {
            observeable.attach(observer);
            int observerAnzahl = observeable.getObserverList().size();
            assertThrows(IllegalArgumentException.class, () -> observeable.attach(observer));
            assertEquals(observerAnzahl, observeable.getObserverList().size());
        }

        /**
         * Es soll nicht möglich sein ein ungültigen Observer anzuhängen. Dafür soll eine
         * IllegalArgumentException geworfen werden und die Observerliste soll unverändert werden.
         */
        @Test
        public void attachNull() {
            assertThrows(IllegalArgumentException.class, () -> observeable.attach(null));
            assertEquals(0, observeable.getObserverList().size());
        }
    }

    @RunWith(MockitoJUnitRunner.class)
    public static class DetachTests {
        IObserveable grosshandel;
        Einzelhandel observer1;
        Einzelhandel observer2;
        Einzelhandel observer3;

        /**
         * Für alle Tests wird ein gültiger Grosshandel erstellt außerdem werden 3 Observer gemockt
         * und die ersten 2 an den grosshandel angehangen.
         */
        @Before
        public void setUp() {
            observer1 = Mockito.mock(Einzelhandel.class);
            observer2 = Mockito.mock(Einzelhandel.class);
            observer3 = Mockito.mock(Einzelhandel.class);
            grosshandel = new Grosshandel();
            grosshandel.attach(observer1);
            grosshandel.attach(observer2);
        }

        @Test
        public void detach() {
            int observedCount = grosshandel.getObserverList().size() - 1;
            grosshandel.detach(observer1);
            assertFalse(grosshandel.getObserverList().contains(observer1));
            assertEquals(observedCount, grosshandel.getObserverList().size());
        }

        @Test
        public void detachNotAttached() {
            int observedCount = grosshandel.getObserverList().size();
            assertThrows(IllegalArgumentException.class, () -> grosshandel.detach(observer3));
            assertFalse(grosshandel.getObserverList().contains(observer3));
            assertEquals(observedCount, grosshandel.getObserverList().size());
        }

        @Test
        public void detachNull() {
            int observedCount = grosshandel.getObserverList().size();
            assertThrows(IllegalArgumentException.class, () -> grosshandel.detach(null));
            assertEquals(observedCount, grosshandel.getObserverList().size());
        }
    }

    @RunWith(MockitoJUnitRunner.class)
    public static class BestellenTests {
        AbstrakterGrosshandel grosshandel;
        Einzelhandel einzelhandel;

        @Before
        public void setUp() {
            einzelhandel = Mockito.mock(Einzelhandel.class);
            grosshandel = new Grosshandel();
        }

        @Test
        public void bestellenAuftragsmengeGroesserAlsLager() {
            grosshandel.lager.put(WarenTyp.Batterien, 1);
            grosshandel.bestellen(einzelhandel, new Auftrag(WarenTyp.Batterien, 2));
            int lagermenge = grosshandel.lager.get(WarenTyp.Batterien);
            assertEquals(lagermenge, 1);
        }

        @Test
        public void bestellenAuftragsmengeKleinerAlsLager() {
            grosshandel.lager.put(WarenTyp.Batterien, 2);
            Auftrag auftrag = new Auftrag(WarenTyp.Batterien, 1);
            grosshandel.bestellen(einzelhandel, auftrag);

            Mockito.verify(einzelhandel).empfangen(auftrag);
            int lagermenge = grosshandel.lager.get(WarenTyp.Batterien);
            assertEquals(lagermenge, 1);
        }

        @Test(expected = IllegalArgumentException.class)
        public void bestellenNullAuftrag() {
            grosshandel.bestellen(einzelhandel, null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void bestellenNullKunde() {
            grosshandel.bestellen(null, new Auftrag(WarenTyp.Batterien, 1));
        }

        @Test(expected = IllegalArgumentException.class)
        public void bestellenNullKundeUndAuftrag() {
            grosshandel.bestellen(null, null);
        }
    }

    @RunWith(MockitoJUnitRunner.class)
    public static class NotifyObserversTests {
        IObserveable grosshandel;
        IObserver observer1;
        IObserver observer2;

        @Before
        public void setUp() {
            observer1 = Mockito.mock(Einzelhandel.class);
            observer2 = Mockito.mock(Einzelhandel.class);
            grosshandel = new Grosshandel();
        }

        @Test
        public void notifyObserversAttachedInteraction() {
            grosshandel.attach(observer1);
            grosshandel.attach(observer2);
            grosshandel.notifyObservers(WarenTyp.Batterien);
            Mockito.verify(observer1).update(WarenTyp.Batterien);
            Mockito.verify(observer2).update(WarenTyp.Batterien);
        }

        @Test
        public void notifyObserversDetachedNoInteraction() {
            grosshandel.attach(observer1);
            grosshandel.attach(observer2);
            grosshandel.detach(observer2);
            grosshandel.notifyObservers(WarenTyp.Batterien);
            Mockito.verify(observer1).update(WarenTyp.Batterien);
            Mockito.verifyNoInteractions(observer2);
        }

        @Test
        public void notifyObserversNoObserver() {
            grosshandel.notifyObservers(WarenTyp.Batterien);
            Mockito.verifyNoInteractions(observer1);
            Mockito.verifyNoInteractions(observer2);
        }

        @Test
        public void notifyObserversNull() {
            grosshandel.attach(observer1);
            grosshandel.attach(observer2);
            assertThrows(IllegalArgumentException.class, () -> grosshandel.notifyObservers(null));
            Mockito.verifyNoInteractions(observer1);
            Mockito.verifyNoInteractions(observer2);
        }
    }

    @RunWith(MockitoJUnitRunner.class)
    public static class LoopTests {
        AbstrakterGrosshandel grosshandel;
        IObserver observer;

        @Before
        public void setUp() {
            grosshandel = new Grosshandel();
            observer = Mockito.mock(Einzelhandel.class);
            grosshandel.attach(observer);
            grosshandel.lager.put(WarenTyp.Bretter, 10);
            grosshandel.lager.put(WarenTyp.Farbeimer, 10);
        }

        @Test
        public void loop() {
            grosshandel.loop();
            Mockito.verify(observer).update(WarenTyp.Batterien);
        }
    }
}
