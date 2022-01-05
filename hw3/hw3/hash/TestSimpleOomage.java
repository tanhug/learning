package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        for (int i = 0; i < 100; i++) {
            SimpleOomage ro = SimpleOomage.randomSimpleOomage();
            SimpleOomage o = new SimpleOomage(ro.red, ro.green, ro.blue);
            SimpleOomage o1 = new SimpleOomage(ro.green, ro.red, ro.blue);
            SimpleOomage o2 = new SimpleOomage(ro.green, ro.blue, ro.red);
            SimpleOomage o3 = new SimpleOomage(ro.red, ro.blue, ro.green);
            SimpleOomage o4 = new SimpleOomage(ro.blue, ro.red, ro.green);
            SimpleOomage o5 = new SimpleOomage(ro.blue, ro.green, ro.red);
            assertEquals(ro.hashCode(), o.hashCode());
            if (ro.red == ro.green) {
                assertEquals(ro.hashCode(), o1.hashCode());
            } else {
                assertNotEquals(ro.hashCode(), o1.hashCode());
            }

            if (ro.blue == ro.green) {
                assertEquals(ro.hashCode(), o3.hashCode());
            } else {
                assertNotEquals(ro.hashCode(), o3.hashCode());
            }

            if (ro.blue == ro.red) {
                assertEquals(ro.hashCode(), o5.hashCode());
            } else {
                assertNotEquals(ro.hashCode(), o5.hashCode());
            }

            if (ro.red == ro.green && ro.green == ro.blue) {
                assertEquals(ro.hashCode(), o2.hashCode());
                assertEquals(ro.hashCode(), o4.hashCode());
            } else {
                assertNotEquals(ro.hashCode(), o2.hashCode());
                assertNotEquals(ro.hashCode(), o4.hashCode());
            }
        }
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
