package visitor;

import tree.Node;

/**
 * Interface for implementing different types of visitors.
 *
 * @param <T> Visited type
 */
public interface INodeVisitor<T extends Comparable<T>> {

    /**
     * Visit the given node.
     *
     * @param node to visit.
     * @return Information you want to get from the (sub) node.
     */
    String visit(Node<T> node);
}
