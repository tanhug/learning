import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offBy5 = new OffByN(5);
    static CharacterComparator offBy1 = new OffByN(1);

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertFalse(offBy5.equalChars('a', 'a'));
        assertFalse(offBy5.equalChars('a', 'B'));
        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('f', 'a'));
        assertTrue(offBy5.equalChars('A', 'F'));
        assertTrue(offBy5.equalChars('B', 'G'));
        assertFalse(offBy5.equalChars('G', 'b'));

        assertFalse(offBy1.equalChars('a', 'a'));
        assertFalse(offBy1.equalChars('a', 'B'));
        assertTrue(offBy1.equalChars('=', '>'));
        assertTrue(offBy1.equalChars('>', '='));
        assertTrue(offBy1.equalChars('X', 'Y'));
        assertTrue(offBy1.equalChars('c', 'd'));
    }
}
