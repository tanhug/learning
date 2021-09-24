package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class BufferIterator<T> implements Iterator<T> {
        int ptr;

        BufferIterator() {
            ptr = first;
        }

        @Override
        public boolean hasNext() {
            return (ptr != last);
        }

        @Override
        public T next() {
            T returnItem = (T) rb[ptr];
            ptr = ptr + 1;
            return returnItem;
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        if (capacity < 1) {
            return;
        }
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        this.capacity = capacity;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        fillCount += 1;
        rb[last] = x;
        last += 1;
        if (last == capacity) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T oldestItem = rb[first];
        fillCount -= 1;
        first += 1;
        if (first == capacity) {
            first = 0;
        }
        return oldestItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator<>();
    }
}
