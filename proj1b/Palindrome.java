public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        char[] c = word.toCharArray();
        if (c[0] == c[c.length - 1]) {
            return isPalindrome(word.substring(1, word.length() - 1));
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        char[] c = word.toCharArray();
        if (cc.equalChars(c[0], c[c.length - 1])) {
            return isPalindrome(word.substring(1, word.length() - 1), cc);
        }
        return false;
    }
}
