import static org.junit.Assert.*;
import org.junit.Test;

public class TestFlik {
    @Test
    public void testIsSameNumber() {
        assertFalse(Flik.isSameNumber(1, 2));
        assertTrue(Flik.isSameNumber(128, 128));
    }
}
