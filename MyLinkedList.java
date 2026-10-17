import java.util.Objects;

/*
 * PROVIDED CLASS - DO NOT MODIFY EXCEPT WHERE MARKED TODO.
 *
 * MyLinkedList stores sortable Strings in fixed-identity nodes. Sorting moves
 * existing nodes structurally; it must never copy/change the String stored by
 * a node or create replacement nodes.
 *
 * A comparison is one ordering test between node values. A move is one transfer
 * of a node between the list and TemporaryNodeStorage. Copying/traversing Java
 * references does not count as a move.
 *
 * extract() is provided only for verification/output and must not be used by
 * sorting implementations.
 */
public final class MyLinkedList {
    // Holds the starting node; cannot read directly (only through getHead())
    private MyNode head;

    /*
     * MyNode stores one sortable String and its next link.
     * The stored String and owning list never change after construction.
     */
    public static final class MyNode {
        // Cannot read these directly outside MyLinkedList
        private final MyLinkedList owned_list;
        private final String element;
        private MyNode next;

        // Cannot construct outside MyLinkedList
        private MyNode(MyLinkedList list, String element) {
            this.owned_list = list;
            this.element = element;
        }

        // Returns the next node.
        MyNode getNext() { return next; }

        // Returns whether this node's element is less than another node's element.
        boolean lessThan(MyNode other) {
            Objects.requireNonNull(other);
            if (owned_list != other.owned_list) throw new IllegalArgumentException("Not a member of the same list");
            SortCounter.incrementComparisonCount();
            return element.compareTo(other.element) < 0;
        }

        // Returns whether this node's element is less than or equal to another node's element.
        boolean lessThanOrEqualTo(MyNode other) {
            Objects.requireNonNull(other);
            if (owned_list != other.owned_list) throw new IllegalArgumentException("Not a member of the same list");
            SortCounter.incrementComparisonCount();
            return element.compareTo(other.element) <= 0;
        }

        // Returns whether this node's element is less than the temporarily stored node's element.
        boolean lessThan(TemporaryNodeStorage other) { return lessThan(other.node); }

        // Returns whether this node's element is less than or equal to the temporarily stored node's element.
        boolean lessThanOrEqualTo(TemporaryNodeStorage other) { return lessThanOrEqualTo(other.node); }

        // Inserts the node from temporary storage immediately after this node.
        void insertNext(TemporaryNodeStorage temp) {
            Objects.requireNonNull(temp);
            MyNode node = temp.node;
            if (owned_list != node.owned_list) throw new IllegalArgumentException("Not a member of the same list");
            SortCounter.incrementMoveCount();
            // --- Do not remove above --- //

            // TODO: Take the node from the temporary storage and insert it next, updating references.
            // Do not create a new MyNode.

            throw new RuntimeException("NOT IMPLEMENTED");
        }

        // Removes the node after this node and returns it in temporary storage.
        TemporaryNodeStorage removeNext() {
            if (next == null) throw new IllegalStateException("No next node");
            SortCounter.incrementMoveCount();
            // --- Do not remove above --- //

            // TODO: If a next node exists, remove it, updating references, and return it in temporary storage.
            // Do not create a new MyNode.

            throw new RuntimeException("NOT IMPLEMENTED");
        }
    }

    /*
     * TemporaryNodeStorage represents temporary algorithm storage for one detached node.
     * Inserting the node consumes the temporary storage; it cannot be reused afterward.
     */
    public static final class TemporaryNodeStorage {
        // Cannot read node directly outside MyLinkedList
        private MyNode node;

        // Cannot construct directly
        private TemporaryNodeStorage(MyNode initial_node) { node = Objects.requireNonNull(initial_node); }

    }

    // Initialize the linked list with our elements
    public MyLinkedList(String[] initial_elements) {
        Objects.requireNonNull(initial_elements);
        for (String element : initial_elements) Objects.requireNonNull(element);

        MyNode tail = null;
        for (String element : initial_elements) {
            MyNode node = new MyNode(this, element);
            if (head == null) head = node;
            else              tail.next = node;
            tail = node;
        }
    }

    // Returns the stored elements in list order without affecting the counters.
    // Should NEVER be called in the sorting algorithms.
    public String[] extract() {
        // Compute size
        int size = 0;
        for (MyNode node = head; node != null; node = node.next) size++;

        String[] elements = new String[size];
        MyNode node = head;
        for (int i = 0; i < size; i++) {
            elements[i] = node.element;
            node        = node.next;
        }
        return elements;
    }

    // Returns the first node.
    MyNode getHead() { return head; }

    // Inserts the node from temporary storage as the list's head.
    void insertFirst(TemporaryNodeStorage temp) {
        Objects.requireNonNull(temp);
        MyNode node = temp.node;
        if (this != node.owned_list) throw new IllegalArgumentException("Not a member of the same list");
        SortCounter.incrementMoveCount();
        // --- Do not remove above --- //

        // TODO: Take the node from the temporary storage and insert it as the head, updating references.
        // Do not create a new MyNode.

        throw new RuntimeException("NOT IMPLEMENTED");
    }

    // Removes the head and returns it in temporary storage.
    TemporaryNodeStorage removeFirst() {
        if (head == null) throw new IllegalStateException("List is empty");
        SortCounter.incrementMoveCount();
        // --- Do not remove above --- //

        // TODO: If a head node exists, remove it, updating references, and return it in temporary storage.
        // Do not create a new MyNode.

        throw new RuntimeException("NOT IMPLEMENTED");
    }
}
