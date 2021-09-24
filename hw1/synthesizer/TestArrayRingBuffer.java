package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(10, arb.capacity());
        assertEquals(3, arb.fillCount());
        assertEquals(1, (int) arb.dequeue());
        assertEquals(10, arb.capacity());
        assertEquals(2, arb.fillCount());
        assertFalse(arb.isEmpty());
        assertFalse(arb.isFull());
        assertEquals(2, (int) arb.peek());
        assertEquals(10, arb.capacity());
        assertEquals(2, arb.fillCount());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        for (int i : arb) {
            System.out.println(i);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
