public class ArrayDeque<T> {
    private int size;
    private int nextFirst, nextLast;
    private T[] items;

    /** Resizes the size of array */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            nextFirst++;
            if (nextFirst == items.length) {
                nextFirst = 0;
            }
            a[i] = items[nextFirst];
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /** Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

//    /** Creates a deep copy of other.*/
//    public ArrayDeque(ArrayDeque other) {
//        items = (T[]) new Object[other.size()];
//        nextFirst = 0;
//        nextLast = 1;
//        size = 0;
//        for (int i = 0; i < other.size(); i++) {
//            addLast((T) other.get(i));
//        }
//    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (item == null) {
            return;
        }
        if (size == items.length) {
            resize(2 * size);
        }
        size++;
        items[nextFirst] = item;
        nextFirst--;
        if (nextFirst == -1) {
            nextFirst += items.length;
        }
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (item == null) {
            return;
        }
        if (size == items.length) {
            resize(2 * size);
        }
        size++;
        items[nextLast] = item;
        nextLast++;
        if (nextLast == items.length) {
            nextLast = 0;
        }
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
        int p = nextFirst;
        for (int i = 0; i < size - 1; i++) {
            p++;
            if (p == items.length) {
                p = 0;
            }
            System.out.print(items[p]);
            System.out.print(" ");
        }
        p++;
        if (p == items.length) {
            p = 0;
        }
        System.out.println(items[p]);
    }

    /** Removes and returns the item at the front of the deque. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst++;
        if (nextFirst == items.length) {
            nextFirst = 0;
        }
        T i = items[nextFirst];
        size--;
        items[nextFirst] = null;
        if (items.length >= 16 && size * 1.0 / items.length < 0.25) {
            resize(items.length / 2);
        }
        return i;
    }

    /** Removes and returns the item at the back of the deque. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        nextLast--;
        if (nextLast == -1) {
            nextLast += items.length;
        }
        T i = items[nextLast];
        items[nextLast] = null;
        if (items.length >= 16 && size * 1.0 / items.length < 0.25) {
            resize(items.length / 2);
        }
        return i;
    }

    /** Gets the item at the given index. */
    public T get(int index) {
        if (size == 0 || size <= index || index < 0) {
            return null;
        }
        return items[(nextFirst + index + 1) % items.length];
    }

}
