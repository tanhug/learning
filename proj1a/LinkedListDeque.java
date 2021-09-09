public class LinkedListDeque<Clown> {
    private Node sentinel;
    private int size;

    private class Node {
        Node prev;
        Node next;
        Clown item;

        /** a constructor of a Node */
        public Node(Clown i, Node p, Node n) {
            prev = p;
            next = n;
            item = i;
        }

        /** another constructor of a Node */
        public Node() {

        }
    }

    /** a constructor of empty LinkedListDeque */
    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Adds an item of type Clown to the front of the deque. */
    public void addFirst(Clown item) {
        if (item == null) {
            return;
        }
        size += 1;
        Node firstOne = sentinel.next;
        Node newOne = new Node(item, sentinel, firstOne);
        firstOne.prev = newOne;
        sentinel.next = newOne;
    }

    /** Adds an item of type Clown to the back of the deque. */
    public void addLast(Clown item) {
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
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
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
    public Clown removeFirst() {
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
    public Clown removeLast() {
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
    public Clown get(int index) {
        if (size == 0 && size <= index) {
            return null;
        }
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

}
