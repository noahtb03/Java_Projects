import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Mark Karev, Noah Bennett
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /**
     * test the constructor.
     */
    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    /**
     * test add on empty heap.
     */
    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    /**
     * test add on nonempty.
     */
    @Test
    public final void testAdd1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b");
        m.add("b");
        assertEquals(mExpected, m);
    }

    /**
     * test add on nonempty large.
     */
    @Test
    public final void testAdd2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                "m");
        m.add("m");
        assertEquals(mExpected, m);
    }

    /**
     * test add with a repeat of elements.
     */
    @Test
    public final void testAdd3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "b");
        m.add("b");
        //despite having another element added to m, m should not change as
        //element added was already in m
        assertEquals(mExpected, m);
    }

    /**
     * test changeToExtractionMode on this so that changes from insertion to
     * extraction.
     */
    @Test
    public final void testChangeToExtractionMode1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a");
        m.changeToExtractionMode();
        /*
         * call changeToExtraction on m, causing the boolean to switch from T to
         * F then create boolean values based on m.isInsertionMode the boolean
         * for m and mExp should both be false
         */
        boolean flag = m.isInInsertionMode();
        boolean flagExp = mExpected.isInInsertionMode();
        assertEquals(flag, flagExp);
    }

    /**
     * test changeToExtractionMode on this so that changes from insertion to
     * extraction, on empty.
     */
    @Test
    public final void testChangeToExtractionMode2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        m.changeToExtractionMode();
        /*
         * call changeToExtraction on m, causing the boolean to switch from T to
         * F then create boolean values based on m.isInsertionMode the boolean
         * for m and mExp should both be false
         */
        boolean flag = m.isInInsertionMode();
        boolean flagExp = mExpected.isInInsertionMode();
        assertEquals(flag, flagExp);
    }

    /**
     * test removeFirst on a single element.
     */
    @Test
    public final void testRemoveFirst1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        m.removeFirst();
        assertEquals(m, mExpected);
    }

    /**
     * test removeFirst in order.
     */
    @Test
    public final void testRemoveFirst2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "b");
        String str = m.removeFirst();
        String strExp = "a";
        assertEquals(m, mExpected);
        assertEquals(str, strExp);
    }

    /**
     * test removeFirst on strings with more values.
     */
    @Test
    public final void testRemoveFirst3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "red",
                "green", "blue", "purple", "black");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "red", "green", "blue", "purple");
        String str = m.removeFirst();
        String strExp = "black";

        assertEquals(m, mExpected);
        assertEquals(str, strExp);
    }

    /**
     * test removeFirst on strings with integer values.
     */
    @Test
    public final void testRemoveFirst4() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "9",
                "8", "2", "4", "5", "1", "7");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "9", "8", "2", "4", "5", "7");
        String str = m.removeFirst();
        String strExp = "1";

        assertEquals(m, mExpected);
        assertEquals(str, strExp);
    }

    /**
     * test isInsertionMode for true.
     */
    @Test
    public final void testIsInsertionMode1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, true, "a");
        //both should return true
        boolean flag = m.isInInsertionMode();
        boolean flagExp = mExp.isInInsertionMode();
        assertEquals(flag, flagExp);
    }

    /**
     * test isInsertionMode on empty for true.
     */
    @Test
    public final void testIsInsertionMode2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, true);
        //both should return true
        boolean flag = m.isInInsertionMode();
        boolean flagExp = mExp.isInInsertionMode();
        assertEquals(flag, flagExp);
    }

    /**
     * test isInsertionMode for false.
     */
    @Test
    public final void testIsInsertionMode3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExp = this.createFromArgsRef(ORDER, false);
        //both should return true
        m.changeToExtractionMode();
        boolean flag = m.isInInsertionMode();
        boolean flagExp = mExp.isInInsertionMode();
        assertEquals(flag, flagExp);
    }

    /**
     * test size on empty.
     */
    @Test
    public final void testSize1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        int i = m.size();
        int iExp = 0;
        assertEquals(i, iExp);
    }

    /**
     * test size on nonempty.
     */
    @Test
    public final void testSize2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        int i = m.size();
        int iExp = 1;
        assertEquals(i, iExp);
    }

    /**
     * test size on larger heap.
     */
    @Test
    public final void testSize3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "9",
                "8", "2", "4", "5", "1", "7");
        int i = m.size();
        final int iExp = 7;
        assertEquals(i, iExp);
    }

    /**
     * test size after calling removeFirst.
     */
    @Test
    public final void testSizeOnRemoveFirst() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b");
        m.removeFirst();
        int i = m.size();
        int iExp = 1;
        assertEquals(i, iExp);
    }

    /**
     * test size after calling add.
     */
    @Test
    public final void testSizeOnAdd() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b");
        m.add("c");
        int i = m.size();
        final int iExp = 3;
        assertEquals(i, iExp);
    }

    /**
     * test order for empty case.
     */
    @Test
    public final void testOrderEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(m.order(), mExpected.order());
    }

    /**
     * test order for regular case.
     */
    @Test
    public final void testOrder1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red",
                "green", "blue", "purple", "black");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "red", "green", "blue", "purple", "black");

        assertEquals(m.order(), mExpected.order());
    }

    /**
     * test order for large case.
     */
    @Test
    public final void testOrder2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "9",
                "8", "2", "4", "5", "1", "7");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "9", "8", "2", "4", "5", "1", "7");

        assertEquals(m.order(), mExpected.order());
    }

}
