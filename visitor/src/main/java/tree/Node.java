package tree;

import org.jetbrains.annotations.NotNull;
import visitor.INodeVisitor;

/**
 * Node in a binary search tree.
 *
 * @param <T> Type to compare. Here card.
 */
public class Node<T extends Comparable<T>> {
    private Node<T> leftChild;
    private Node<T> rightChild;
    private T data;

    /**
     * Create a new node without children.
     *
     * @param data Vehicle to store
     */
    public Node(@NotNull T data) {
        this.data = data;
    }

    private static <T extends Comparable<T>> Node<T> addDataToSubTree(Node<T> node, T data) {
        if (node == null) {
            // Create a new node if this branch does not yet exist
            node = new Node<>(data);
        } else {
            // Otherwise, add data to this existing branch
            node.addData(data);
        }
        return node;
    }

    private static <T extends Comparable<T>> Node<T> clone(Node<T> n) {
        Node<T> clone = null;

        if (n != null) {
            clone = new Node<>(n.getData());
            clone.leftChild = clone(n.getLeftChild());
            clone.rightChild = clone(n.getRightChild());
        }

        return clone;
    }

    /**
     * Add a vehicle to the tree.
     *
     * <p>If the vehicle already exists in a node in the tree, the old vehicle is replaced by the
     * new vehicle in that node. Otherwise, a new node without children is created and added to the
     * correct place in the tree.
     *
     * @param data Vehicle to be inserted
     */
    public void addData(@NotNull T data) {
        final int compareVal = this.data.compareTo(data);

        if (compareVal == 0) {
            // Overwrite existing entry
            this.data = data;
        } else if (compareVal < 0) {
            // Search right subtree
            rightChild = addDataToSubTree(rightChild, data);
        } else {
            // Search left subtree
            leftChild = addDataToSubTree(leftChild, data);
        }
    }

    /**
     * Accept a visitor to this node.
     *
     * @param visitor the visitor.
     * @return Result of the visit
     */
    public String accept(@NotNull INodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    /**
     * @return The left child node of this node.
     */
    public Node<T> getLeftChild() {
        return clone(leftChild);
    }

    /**
     * @return The right child node of this node.
     */
    public Node<T> getRightChild() {
        return clone(rightChild);
    }

    /**
     * @return The stored data in this node.
     */
    public T getData() {
        return data;
    }
}
