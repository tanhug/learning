import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("Noon"));
        assertFalse(palindrome.isPalindrome("Aaaab"));
        assertFalse(palindrome.isPalindrome("Aaaab", cc));
        assertTrue(palindrome.isPalindrome("A", cc));
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("FLAKE", cc));
        assertFalse(palindrome.isPalindrome("fLake", cc));
        assertTrue(palindrome.isPalindrome("acdb", cc));
        assertFalse(palindrome.isPalindrome("aa", cc));
        assertTrue(palindrome.isPalindrome("ab", cc));
    }
}
