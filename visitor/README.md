# Implementierung des Visitor-Patterns

In den Vorgaben finden Sie die Klasse `tree.Node` zur Realisierung von binären Suchbäumen, die verschiedene
[Quartettkarten](https://de.wikipedia.org/wiki/Quartett_(Kartenspiel)) (`card.Card`) speichern können.

Implementieren Sie das Visitor-Pattern für den Baum (Klasse `tree.Node`):

1.  Erstellen Sie einen konkreten Visitor `visitor.InOrderVisitor`, der den Baum _inorder_ traversiert.
2.  Erstellen Sie einen weiteren konkreten Visitor `visitor.PostOrderVisitor`, der den Baum _postorder_ traversiert.

Beim Besuch eines Knotens soll die Methode `card.Card#toString()` aufgerufen werden und passend mit den Ergebnissen
der Traversierung der linken und rechten Teilbäume konkateniert werden und der resultierende String zurückgeben werden.
