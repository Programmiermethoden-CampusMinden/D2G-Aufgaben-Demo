package visitor;

import static org.junit.Assert.assertEquals;

import card.Card;
import card.CardType;
import org.junit.Test;
import tree.Node;

public class InOrderVisitorTest {

    /** Tests with a one-element tree. */
    @Test
    public void testVisitSingleNode() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 10);

        Node<Card> n = new Node<>(c1);
        // A(, )

        INodeVisitor<Card> v = new InOrderVisitor<>();
        String expected = "CAR: 'A'; ";

        assertEquals(expected, n.accept(v));
    }

    /** Tests with two elements, where the left child is smaller than the root element. */
    @Test
    public void testVisitTwoNodesLeft() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 200);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 1);

        Node<Card> n = new Node<>(c1);
        n.addData(c2);
        // A(B(,), )

        INodeVisitor<Card> v = new InOrderVisitor<>();
        String expected = "CAR: 'B'; CAR: 'A'; ";

        assertEquals(expected, n.accept(v));
    }

    /** Tests with two elements, where the right child is bigger than the root element. */
    @Test
    public void testVisitTwoNodesRight() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 1);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 200);

        Node<Card> n = new Node<>(c1);
        n.addData(c2);
        // A(, B(,))

        INodeVisitor<Card> v = new InOrderVisitor<>();
        String expected = "CAR: 'A'; CAR: 'B'; ";

        assertEquals(expected, n.accept(v));
    }

    /**
     * Tests with three elements, where the second and third elements are left and right child of
     * the root element (balanced tree).
     */
    @Test
    public void testVisitThreeNodesBalanced() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 50);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 100);
        Card c3 = new Card(CardType.CAR, "C", 22, 33, 44, 1);

        Node<Card> n = new Node<>(c1);
        n.addData(c2);
        n.addData(c3);
        // A(C(,), B(,))

        INodeVisitor<Card> v = new InOrderVisitor<>();
        String expected = "CAR: 'C'; CAR: 'A'; CAR: 'B'; ";

        assertEquals(expected, n.accept(v));
    }

    /**
     * Tests with three elements, where the second and third elements are each inserted in the left
     * tree below the root element.
     */
    @Test
    public void testVisitThreeNodesLeftLeft() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 200);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 50);
        Card c3 = new Card(CardType.CAR, "C", 22, 33, 44, 1);

        Node<Card> n = new Node<>(c1);
        n.addData(c2);
        n.addData(c3);
        // A(B(C(,),), )

        INodeVisitor<Card> v = new InOrderVisitor<>();
        String expected = "CAR: 'C'; CAR: 'B'; CAR: 'A'; ";

        assertEquals(expected, n.accept(v));
    }

    /**
     * Tests with three elements, where the second element is inserted to the left of the root
     * element and the third element is inserted to the right of the second element.
     */
    @Test
    public void testVisitThreeNodesLeftRight() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 200);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 50);
        Card c3 = new Card(CardType.CAR, "C", 22, 33, 44, 100);

        Node<Card> n = new Node<>(c1);
        n.addData(c2);
        n.addData(c3);
        // A(B(, C(,)), )

        INodeVisitor<Card> v = new InOrderVisitor<>();
        String expected = "CAR: 'B'; CAR: 'C'; CAR: 'A'; ";

        assertEquals(expected, n.accept(v));
    }

    /**
     * Tests with three elements, where the second element is inserted to the right of the root
     * element and the third element is inserted to the left of the second element.
     */
    @Test
    public void testVisitThreeNodesRightLeft() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 1);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 200);
        Card c3 = new Card(CardType.CAR, "C", 22, 33, 44, 50);

        Node<Card> n = new Node<>(c1);
        n.addData(c2);
        n.addData(c3);
        // A(, B(C(,),))

        INodeVisitor<Card> v = new InOrderVisitor<>();
        String expected = "CAR: 'A'; CAR: 'C'; CAR: 'B'; ";

        assertEquals(expected, n.accept(v));
    }

    /**
     * Tests with three elements, where the second and third elements are each inserted into the
     * right subtree of the root element.
     */
    @Test
    public void testVisitThreeNodesRightRight() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 1);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 50);
        Card c3 = new Card(CardType.CAR, "C", 22, 33, 44, 200);

        Node<Card> n = new Node<>(c1);
        n.addData(c2);
        n.addData(c3);
        // A(, B(, C(,)))

        INodeVisitor<Card> v = new InOrderVisitor<>();
        String expected = "CAR: 'A'; CAR: 'B'; CAR: 'C'; ";

        assertEquals(expected, n.accept(v));
    }

    /**
     * Tests with four elements. The second element is inserted to the right of the root, the third
     * to the right of the second, and the fourth to the left of the root.
     */
    @Test
    public void testVisitLargerTree() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 10);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 50);
        Card c3 = new Card(CardType.CAR, "C", 22, 33, 44, 200);
        Card c4 = new Card(CardType.CAR, "D", 22, 33, 44, 1);

        Node<Card> n = new Node<>(c1);
        n.addData(c2); // c1 < c2: add c2 as new right child
        n.addData(c3); // c1 < c2 < c3: add c3 as new right-right child
        n.addData(c4); // c1 > c4: add c4 as new left child
        // A(D(,), B(,C))

        INodeVisitor<Card> v = new InOrderVisitor<>();
        String expected = "CAR: 'D'; CAR: 'A'; CAR: 'B'; CAR: 'C'; ";

        assertEquals(expected, n.accept(v));
    }

    /** Tests: The visit method with a bigger (balanced) tree. */
    @Test
    public void testVisitBiggerTree() {
        Card c1 = new Card(CardType.CAR, "Car_3", 5, 0, 0, 120);
        Card c2 = new Card(CardType.BOAT, "Boat_3", 113, 156, 11, 1200);
        Card c3 = new Card(CardType.MOTORCYCLE, "Motorcycle_3", 41, 251, 8, 1300);
        Card c4 = new Card(CardType.MOTORCYCLE, "Motorcycle_1", 21, 105, 3, 1500);
        Card c5 = new Card(CardType.MOTORCYCLE, "Motorcycle_4", 12, 161, 4, 1500);
        Card c6 = new Card(CardType.MOTORCYCLE, "Motorcycle_2", 15, 510, 8, 2000);
        Card c7 = new Card(CardType.AIRPLANE, "Airplane_4", 12342, 14563, 1600, 5367);
        Card c8 = new Card(CardType.CAR, "Car_1", 350, 120, 10, 8050);

        Card c9 = new Card(CardType.CAR, "Car_4", 450, 500, 7, 9000);

        Card c10 = new Card(CardType.BOAT, "Boat_1", 115, 115, 14, 11111);
        Card c11 = new Card(CardType.BOAT, "Boat_4", 131, 185, 18, 11612);
        Card c12 = new Card(CardType.BOAT, "Boat_2", 112, 16, 12, 15000);
        Card c13 = new Card(CardType.AIRPLANE, "Airplane_3", 135, 14568, 1900, 67923);
        Card c14 = new Card(CardType.CAR, "Car_5", 1550, 30, 7000, 90000);
        Card c15 = new Card(CardType.AIRPLANE, "Airplane_2", 45671, 16543, 2100, 112389);
        Card c16 = new Card(CardType.AIRPLANE, "Airplane_1", 12341, 15654, 2300, 123123);
        Card c17 = new Card(CardType.CAR, "Car_2", 1000, 1230, 35, 300050);

        Node<Card> n = new Node<>(c9);
        n.addData(c8);
        n.addData(c7);
        n.addData(c6);
        n.addData(c5);
        n.addData(c4);
        n.addData(c3);
        n.addData(c2);
        n.addData(c1);
        n.addData(c10);
        n.addData(c11);
        n.addData(c12);
        n.addData(c13);
        n.addData(c14);
        n.addData(c15);
        n.addData(c16);
        n.addData(c17);

        INodeVisitor<Card> v = new InOrderVisitor<>();
        String expected =
                "CAR: 'Car_3'; BOAT: 'Boat_3'; MOTORCYCLE: 'Motorcycle_3'; MOTORCYCLE:"
                    + " 'Motorcycle_1'; MOTORCYCLE: 'Motorcycle_4'; MOTORCYCLE: 'Motorcycle_2';"
                    + " AIRPLANE: 'Airplane_4'; CAR: 'Car_1'; CAR: 'Car_4'; BOAT: 'Boat_1'; BOAT:"
                    + " 'Boat_4'; BOAT: 'Boat_2'; AIRPLANE: 'Airplane_3'; CAR: 'Car_5'; AIRPLANE:"
                    + " 'Airplane_2'; AIRPLANE: 'Airplane_1'; CAR: 'Car_2'; ";

        assertEquals(expected, n.accept(v));
    }
}
