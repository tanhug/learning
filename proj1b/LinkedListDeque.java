public class LinkedListDeque<T> implements Deque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        Node prev;
        Node next;
        T item;

        /** a constructor of a Node */
        public Node(T i, Node p, Node n) {
            prev = p;
            next = n;
            item = i;
        }

        /** another constructor of a Node */
        public Node() {

        }
    }

    /** A constructor of empty LinkedListDeque */
    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Creates a deep copy of other. */
//    public LinkedListDeque(LinkedListDeque other) {
//        size = 0;
//        sentinel = new Node();
//        sentinel.next = sentinel;
//        sentinel.prev = sentinel;
//        for (int i = 0; i < other.size(); i++) {
//            addLast((T) other.get(i));
//        }
//    }

    /** Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(T item) {
        if (item == null) {
            return;
        }
        size += 1;
        Node firstOne = sentinel.next;
        Node newOne = new Node(item, sentinel, firstOne);
        firstOne.prev = newOne;
        sentinel.next = newOne;
    }

    /** Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T item) {
        if (item == null) {
            return;
        }
        size += 1;
        Node lastOne = sentinel.prev;
        Node newOne = new Node(item, lastOne, sentinel);
        lastOne.next = newOne;
        sentinel.prev = newOne;

    }

    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    @Override
    public void printDeque() {
        if (size == 0) {
            return;
        }
        Node p = sentinel.next;
        Node lastOne = sentinel.prev;
        while (p != lastOne) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.println(p.item);
    }

    /** Removes and returns the item at the front of the deque. */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Node firstOne = sentinel.next;
        sentinel.next = firstOne.next;
        firstOne.next.prev = sentinel;
        return firstOne.item;
    }

    /** Removes and returns the item at the back of the deque. */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Node lastOne = sentinel.prev;
        sentinel.prev = lastOne.prev;
        lastOne.prev.next = sentinel;
        return lastOne.item;
    }

    /** Gets the item at the given index. */
    @Override
    public T get(int index) {
        if (size == 0 || size <= index || index < 0) {
            return null;
        }
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    /** a private method for helping the implementation of getRecursive() */
    private T getRecursive(int index, Node p) {
        if (index == 0) {
            return p.item;
        }
        p = p.next;
        return getRecursive(index - 1, p);
    }

    /** Gets the item at the given index.(a recursive version) */
    public T getRecursive(int index) {
        if (size == 0 || size <= index || index < 0) {
            return null;
        }
        Node p = sentinel.next;
        return getRecursive(index, p);
    }

}
