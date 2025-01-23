import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Mark Karev, Noah Bennett
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * test constructor.
     */
    @Test
    public final void testConstructor() {
        Set<String> s = this.constructorTest();
        Set<String> sExp = this.constructorRef();
        assertEquals(s, sExp);

    }

    /**
     * test add with single element to an empty set.
     */
    @Test
    public final void testAdd1() {
        Set<String> s = this.createFromArgsTest();
        String str = "a";
        s.add(str);
        Set<String> sExp = this.createFromArgsRef("a");
        assertEquals(s, sExp);

    }

    /**
     * test adding an element to a nonempty set small.
     */
    @Test
    public final void testAdd2() {
        Set<String> s = this.createFromArgsTest("a");
        String str = "b";
        s.add(str);
        Set<String> sExp = this.createFromArgsRef("a", "b");
        assertEquals(s, sExp);

    }

    /**
     * test adding an element to a set multiple times.
     */
    @Test
    public final void testAdd3() {
        Set<String> s = this.createFromArgsTest("a");
        String str1 = "b";
        String str2 = "c";
        s.add(str1);
        s.add(str2);
        Set<String> sExp = this.createFromArgsRef("a", "b", "c");
        assertEquals(s, sExp);

    }

    /**
     * test adding an empty string to a set .
     */
    @Test
    public final void testAdd4() {
        Set<String> s = this.createFromArgsTest("a");
        String str = "";
        s.add(str);
        Set<String> sExp = this.createFromArgsRef("", "a");
        assertEquals(s, sExp);
    }

    /**
     * test adding a string to a large set .
     */
    @Test
    public final void testAdd5() {
        Set<String> s = this.createFromArgsTest("m", "b", "g", "p", "d", "z",
                "e", "c");
        String str = "h";
        s.add(str);
        Set<String> sExp = this.createFromArgsRef("m", "b", "g", "p", "d", "z",
                "e", "c", "h");
        assertEquals(s, sExp);
    }

    /**
     * test removing an element from a small set.
     */
    @Test
    public final void testRemove1() {
        Set<String> s = this.createFromArgsTest("a", "b");
        s.remove("a");
        Set<String> sExp = this.createFromArgsRef("b");
        assertEquals(s, sExp);

    }

    /**
     * test remove on a large set with no left subtree.
     */
    @Test
    public final void testRemove2() {
        Set<String> s = this.createFromArgsTest("a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l");
        s.remove("a");
        Set<String> sExp = this.createFromArgsRef("b", "c", "d", "e", "f", "g",
                "h", "i", "j", "k", "l");
        assertEquals(s, sExp);
    }

    /**
     * test remove on a large set with no right subtree.
     */
    @Test
    public final void testRemove3() {
        Set<String> s = this.createFromArgsTest("l", "k", "j", "i", "h", "g",
                "f", "e", "d", "c", "b", "a");
        s.remove("a");
        Set<String> sExp = this.createFromArgsRef("l", "k", "j", "i", "h", "g",
                "f", "e", "d", "c", "b");
        assertEquals(s, sExp);
    }

    /**
     * test remove on a large set.
     */
    @Test
    public final void testRemove4() {
        Set<String> s = this.createFromArgsTest("f", "b", "g", "p", "d", "z",
                "e", "c");
        s.remove("c");
        Set<String> sExp = this.createFromArgsRef("f", "b", "g", "p", "d", "z",
                "e");
        assertEquals(s, sExp);
    }

    /**
     * test removing multiple elements.
     */
    @Test
    public final void testRemove5() {
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        s.remove("a");
        s.remove("c");
        Set<String> sExp = this.createFromArgsRef("b");
        assertEquals(s, sExp);
    }

    /**
     * test removeAny.
     */
    @Test
    public final void testRemoveAny1() {
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        String element = s.removeAny();
        Set<String> sExp = this.createFromArgsRef("a", "b", "c");
        //remove the element from sExp that removeAny had removed from s
        //and compare if they are equal
        sExp.remove(element);
        assertEquals(s, sExp);
    }

    /**
     * test removeAny on a single element set.
     */
    @Test
    public final void testRemoveAny2() {
        Set<String> s = this.createFromArgsTest("a");
        s.removeAny();
        Set<String> sExp = this.createFromArgsRef();
        assertEquals(s, sExp);
    }

    /**
     * test removeAny on a larger set.
     */
    @Test
    public final void testRemoveAny3() {
        Set<String> s = this.createFromArgsTest("9", "15", "2", "6", "10", "25",
                "5");
        String str = s.removeAny();
        Set<String> sExp = this.createFromArgsRef("9", "15", "2", "6", "10",
                "25", "5");
        sExp.remove(str);
        assertEquals(s, sExp);
    }

    /**
     * test contains for true.
     */
    @Test
    public final void testContains1() {
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        boolean flag = s.contains("b");
        assertEquals(flag, true);
    }

    /**
     * test contains for false.
     */
    @Test
    public final void testContains2() {
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        boolean flag = s.contains("x");
        assertEquals(flag, false);
    }

    /**
     * test contains on an empty set.
     */
    @Test
    public final void testContains3() {
        Set<String> s = this.createFromArgsTest();
        boolean flag = s.contains("x");
        assertEquals(flag, false);
    }

    /**
     * test contains on an empty set.
     */
    @Test
    public final void testContains4() {
        Set<String> s = this.createFromArgsTest();
        boolean flag = s.contains("");
        assertEquals(flag, false);
    }

    /**
     * test true contains on a large set.
     */
    @Test
    public final void testContains5() {
        Set<String> s = this.createFromArgsTest("9", "15", "2", "6", "10", "25",
                "5");
        boolean flag = s.contains("6");
        assertEquals(flag, true);
    }

    /**
     * test size on an empty set.
     */
    @Test
    public final void testSize1() {
        Set<String> s = this.createFromArgsTest();
        int size = s.size();
        int sizeExp = 0;
        assertEquals(size, sizeExp);
    }

    /**
     * test size on a nonempty set.
     */
    @Test
    public final void testSize2() {
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        int size = s.size();
        int sizeExp = 3;
        assertEquals(size, sizeExp);
    }

    /**
     * test size after calling add.
     */
    @Test
    public final void testSize3() {
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        s.add("d");
        int size = s.size();
        //size should be one greater than the original size of the set
        int sizeExp = 4;
        assertEquals(size, sizeExp);
    }

    /**
     * test size after calling remove.
     */
    @Test
    public final void testSize4() {
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        s.remove("a");
        int size = s.size();
        //size should be one less than the original size of the set
        int sizeExp = 2;
        assertEquals(size, sizeExp);
    }

    /**
     * test size after calling removeAny.
     */
    @Test
    public final void testSize5() {
        Set<String> s = this.createFromArgsTest("a", "b", "c");
        s.removeAny();
        int size = s.size();
        //size should be one less than original size of the set
        int sizeExp = 2;
        assertEquals(size, sizeExp);
    }

}
