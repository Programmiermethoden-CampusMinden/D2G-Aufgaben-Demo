package tree;

import static org.junit.Assert.*;

import card.Card;
import card.CardType;
import org.junit.Test;

public class NodeTest {

    /**
     * Tests the constructor of Node.
     *
     * <p>Tests with an empty tree.
     */
    @Test
    public void testNode() {
        Card c = new Card(CardType.CAR, "A", 22, 33, 44, 55);

        Node<Card> n = new Node<>(c);
        // A(,)

        assertEquals(c, n.getData());
        assertNull(n.getLeftChild());
        assertNull(n.getRightChild());
    }

    /**
     * Tests the constructor and addData method of Node.
     *
     * <p>Tests with two elements with the same attributes.
     */
    @Test
    public void testAddDataDuplicate() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 55);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 55);

        Node<Card> n = new Node<>(c1);
        n.addData(c2); // c1 == c2: overwrite c1
        // B(,)

        assertEquals(c2, n.getData());
        assertNull(n.getLeftChild());
        assertNull(n.getRightChild());
    }

    /**
     * Tests the constructor and addData method of Node.
     *
     * <p>Tests with two elements, where the left child is smaller than the root element.
     */
    @Test
    public void testAddDataEmptyLeft() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 200);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 1);

        Node<Card> n = new Node<>(c1);
        n.addData(c2); // c1 > c2: add c2 as new left child
        // A(B(,), )

        assertEquals(c1, n.getData());
        assertNotNull(n.getLeftChild());
        assertNull(n.getRightChild());

        assertEquals(c2, n.getLeftChild().getData());
        assertNull(n.getLeftChild().getLeftChild());
        assertNull(n.getLeftChild().getRightChild());
    }

    /**
     * Tests the constructor and addData method of Node.
     *
     * <p>Tests with two elements, where the right child is bigger than the root element.
     */
    @Test
    public void testAddDataEmptyRight() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 1);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 200);

        Node<Card> n = new Node<>(c1);
        n.addData(c2); // c1 < c2: add c2 as new right child
        // A(, B(,))

        assertEquals(c1, n.getData());
        assertNull(n.getLeftChild());
        assertNotNull(n.getRightChild());

        assertEquals(c2, n.getRightChild().getData());
        assertNull(n.getRightChild().getLeftChild());
        assertNull(n.getRightChild().getRightChild());
    }

    /**
     * Tests the constructor and addData method of Node.
     *
     * <p>Tests with three elements, where the left child is smaller than the root element and the
     * third element is equal to the left child. The left child element should be overwritten.
     */
    @Test
    public void testAddDataDuplicateRecursiveLeft() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 200);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 1);
        Card c3 = new Card(CardType.CAR, "C", 22, 33, 44, 1);

        Node<Card> n = new Node<>(c1);
        n.addData(c2); // c1 > c2: add c2 as new left child
        n.addData(c3); // c2 == c3: overwrite c2
        // A(C(,), )

        assertEquals(c1, n.getData());
        assertNotNull(n.getLeftChild());
        assertNull(n.getRightChild());

        assertEquals(c3, n.getLeftChild().getData());
        assertNull(n.getLeftChild().getLeftChild());
        assertNull(n.getLeftChild().getRightChild());
    }

    /**
     * Tests the constructor and addData method of Node.
     *
     * <p>Tests with three elements, where the right child is bigger than the root element and the
     * third element is equal to the right child. The right child element should be overwritten.
     */
    @Test
    public void testAddDataDuplicateRecursiveRight() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 1);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 200);
        Card c3 = new Card(CardType.CAR, "C", 22, 33, 44, 200);

        Node<Card> n = new Node<>(c1);
        n.addData(c2); // c1 < c2: add c2 as new right child
        n.addData(c3); // c2 == c3: overwrite c2
        // A(, C(,))

        assertEquals(c1, n.getData());
        assertNull(n.getLeftChild());
        assertNotNull(n.getRightChild());

        assertEquals(c3, n.getRightChild().getData());
        assertNull(n.getRightChild().getLeftChild());
        assertNull(n.getRightChild().getRightChild());
    }

    /**
     * Tests the constructor and addData method of Node.
     *
     * <p>Tests with three elements, with the second and third elements each to be inserted in the
     * left tree below the root element.
     */
    @Test
    public void testAddDataEmptyLL() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 200);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 50);
        Card c3 = new Card(CardType.CAR, "C", 22, 33, 44, 1);

        Node<Card> n = new Node<>(c1);
        n.addData(c2); // c1 > c2: add c2 as new left child
        n.addData(c3); // c1 > c2 > c3: add c3 as new left-left child
        // A(B(C(,),), )

        assertEquals(c1, n.getData());
        assertNotNull(n.getLeftChild());
        assertNull(n.getRightChild());

        assertEquals(c2, n.getLeftChild().getData());
        assertNotNull(n.getLeftChild().getLeftChild());
        assertNull(n.getLeftChild().getRightChild());

        assertEquals(c3, n.getLeftChild().getLeftChild().getData());
        assertNull(n.getLeftChild().getLeftChild().getLeftChild());
        assertNull(n.getLeftChild().getLeftChild().getRightChild());
    }

    /**
     * Tests the constructor and addData method of Node.
     *
     * <p>Tests with three elements, where the second element should be inserted to the left of the
     * root element and the third element to the right of the second element.
     */
    @Test
    public void testAddDataEmptyLR() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 200);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 50);
        Card c3 = new Card(CardType.CAR, "C", 22, 33, 44, 100);

        Node<Card> n = new Node<>(c1);
        n.addData(c2); // c1 > c2: add c2 as new left child
        n.addData(c3); // c1 > c3 > c2: add c3 as new left-right child
        // A(B(, C(,)), )

        assertEquals(c1, n.getData());
        assertNotNull(n.getLeftChild());
        assertNull(n.getRightChild());

        assertEquals(c2, n.getLeftChild().getData());
        assertNull(n.getLeftChild().getLeftChild());
        assertNotNull(n.getLeftChild().getRightChild());

        assertEquals(c3, n.getLeftChild().getRightChild().getData());
        assertNull(n.getLeftChild().getRightChild().getLeftChild());
        assertNull(n.getLeftChild().getRightChild().getRightChild());
    }

    /**
     * Tests the constructor and addData method of Node.
     *
     * <p>Tests with three elements, where the second element should be inserted to the right of the
     * root element and the third element to the left of the second element.
     */
    @Test
    public void testAddDataEmptyRL() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 1);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 200);
        Card c3 = new Card(CardType.CAR, "C", 22, 33, 44, 50);

        Node<Card> n = new Node<>(c1);
        n.addData(c2); // c1 < c2: add c2 as new right child
        n.addData(c3); // c1 < c3 < c2: add c3 as new right-left child
        // A(, B(C(,),))

        assertEquals(c1, n.getData());
        assertNull(n.getLeftChild());
        assertNotNull(n.getRightChild());

        assertEquals(c2, n.getRightChild().getData());
        assertNotNull(n.getRightChild().getLeftChild());
        assertNull(n.getRightChild().getRightChild());

        assertEquals(c3, n.getRightChild().getLeftChild().getData());
        assertNull(n.getRightChild().getLeftChild().getLeftChild());
        assertNull(n.getRightChild().getLeftChild().getRightChild());
    }

    /**
     * Tests the constructor and addData method of Node.
     *
     * <p>Tests with three elements, where the second and third elements are each to be inserted
     * into the right subtree of the root element.
     */
    @Test
    public void testAddDataEmptyRR() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 1);
        Card c2 = new Card(CardType.CAR, "B", 22, 33, 44, 50);
        Card c3 = new Card(CardType.CAR, "C", 22, 33, 44, 200);

        Node<Card> n = new Node<>(c1);
        n.addData(c2); // c1 < c2: add c2 as new right child
        n.addData(c3); // c1 < c2 < c3: add c3 as new right-right child
        // A(, B(, C(,)))

        assertEquals(c1, n.getData());
        assertNull(n.getLeftChild());
        assertNotNull(n.getRightChild());

        assertEquals(c2, n.getRightChild().getData());
        assertNull(n.getRightChild().getLeftChild());
        assertNotNull(n.getRightChild().getRightChild());

        assertEquals(c3, n.getRightChild().getRightChild().getData());
        assertNull(n.getRightChild().getRightChild().getLeftChild());
        assertNull(n.getRightChild().getRightChild().getRightChild());
    }

    /**
     * Tests the constructor and accept method of Node.
     *
     * <p>Tests the accept method of Node with a one-element tree.
     */
    @Test
    public void testAcceptVisitor() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 10);
        Node<Card> n = new Node<>(c1);
        // A(, )

        String expected = "CAR: 'A'; ";

        assertEquals(expected, n.accept(node -> node.getData().toString()));
    }

    /**
     * Tests the constructor and getLeftChild method of Node.
     *
     * <p>Tests if getLeftChild() returns a deep copy of the subtree.
     */
    @Test
    public void testNodeGetLeftChild() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 10);
        Card c2 = new Card(CardType.CAR, "A", -555, 33, 44, 10);

        Node<Card> n = new Node<>(c1);
        assertNull(n.getLeftChild());

        n.addData(c2);
        Node<Card> child = n.getLeftChild();
        assertNotNull(child);
        assertNotSame(n, child);
        assertNotEquals(n, child);

        assertEquals(c2, child.getData());
    }

    /**
     * Tests the constructor and getRightChild method of Node.
     *
     * <p>Tests if getRightChild() returns a deep copy of the subtree.
     */
    @Test
    public void testNodeGetRightChild() {
        Card c1 = new Card(CardType.CAR, "A", 22, 33, 44, 10);
        Card c2 = new Card(CardType.CAR, "A", 555, 33, 44, 10);

        Node<Card> n = new Node<>(c1);
        assertNull(n.getLeftChild());

        n.addData(c2);
        Node<Card> child = n.getRightChild();
        assertNotNull(child);
        assertNotSame(n, child);
        assertNotEquals(n, child);

        assertEquals(c2, child.getData());
    }
}
