import static org.junit.Assert.assertEquals;

import java.util.AbstractMap;
import java.util.Map;

import org.junit.Test;

public class hashArrTest {

    @Test
    public void testInsert1() {
        hashArr hs = new hashArr(3);
        hs.insert(0, "hello");
        hs.insert(4, "hi");
        hs.insert(0, "hey");
        Map.Entry<Integer, String> hs0 = new AbstractMap.SimpleEntry<>(0,
                "hello");
        Map.Entry<Integer, String> hs1 = new AbstractMap.SimpleEntry<>(4, "hi");
        Map.Entry<Integer, String> hs2 = new AbstractMap.SimpleEntry<>(0,
                "hey");

        assertEquals(hs.table[0], hs0);
        assertEquals(hs.table[1], hs1);
        assertEquals(hs.table[2], hs2);
    }

    @Test
    public void testInsert2() {
        hashArr hs = new hashArr(10);
        hs.insert(1902, "hello");
        hs.insert(1902, "hi");
        hs.insert(658, "hey");
        Map.Entry<Integer, String> hs0 = new AbstractMap.SimpleEntry<>(1902,
                "hello");
        Map.Entry<Integer, String> hs1 = new AbstractMap.SimpleEntry<>(1902,
                "hi");
        Map.Entry<Integer, String> hs2 = new AbstractMap.SimpleEntry<>(658,
                "hey");

        assertEquals(hs.table[2], hs0);
        assertEquals(hs.table[3], hs1);
        assertEquals(hs.table[8], hs2);
    }

    @Test
    public void testInsert3() {
        hashArr hs = new hashArr(500);
        hs.insert(0, "hello");
        hs.insert(0, "hi");
        hs.insert(0, "hey");
        Map.Entry<Integer, String> hs0 = new AbstractMap.SimpleEntry<>(0,
                "hello");
        Map.Entry<Integer, String> hs1 = new AbstractMap.SimpleEntry<>(0, "hi");
        Map.Entry<Integer, String> hs2 = new AbstractMap.SimpleEntry<>(0,
                "hey");

        assertEquals(hs.table[0], hs0);
        assertEquals(hs.table[1], hs1);
        assertEquals(hs.table[2], hs2);
    }

    @Test
    public void testRetrieve1() {
        hashArr hs = new hashArr(500);
        hs.insert(0, "hello");
        hs.insert(0, "hi");
        hs.insert(0, "hey");
        Map.Entry<Integer, String> retrieve = hs.retrieve(0, "hi");
        Map.Entry<Integer, String> check = new AbstractMap.SimpleEntry<>(0,
                "hi");

        assertEquals(retrieve, check);
    }

    @Test
    public void testRetrieve2() {
        hashArr hs = new hashArr(500);
        hs.insert(1902, "hello");
        hs.insert(1902, "hi");
        hs.insert(658, "hey");
        Map.Entry<Integer, String> retrieve = hs.retrieve(1902, "hello");
        Map.Entry<Integer, String> check = new AbstractMap.SimpleEntry<>(1902,
                "hello");

        assertEquals(retrieve, check);
    }

    @Test
    public void testRetrieve3() {
        hashArr hs = new hashArr(500);
        hs.insert(44, "blue");
        hs.insert(12, "yello");
        hs.insert(809, "green");
        Map.Entry<Integer, String> retrieve = hs.retrieve(809, "green");
        Map.Entry<Integer, String> check = new AbstractMap.SimpleEntry<>(809,
                "green");

        assertEquals(retrieve, check);
    }

    @Test
    public void testMember1() {
        hashArr hs = new hashArr(500);
        hs.insert(44, "blue");
        hs.insert(12, "yello");
        hs.insert(809, "green");
        boolean member = hs.member(0, "purple");

        assertEquals(member, false);
    }

    @Test
    public void testMember2() {
        hashArr hs = new hashArr(500);
        hs.insert(44, "blue");
        hs.insert(12, "yello");
        hs.insert(809, "green");
        boolean member = hs.member(44, "blue");

        assertEquals(member, true);
    }

    @Test
    public void testMember3() {
        hashArr hs = new hashArr(500);
        hs.insert(44, "blue");
        hs.insert(12, "yello");
        hs.insert(809, "green");
        boolean member = hs.member(809, "green");

        assertEquals(member, true);
    }

    @Test
    public void testDelete1() {
        hashArr hs = new hashArr(500);
        hashArr hs2 = new hashArr(500);
        hs.insert(44, "blue");
        hs.insert(12, "yello");
        hs.insert(809, "green");
        hs.insert(12, "brown");
        hs.insert(306, "orange");
        hs2.insert(44, "blue");
        hs2.insert(12, "yello");
        hs2.insert(809, "green");
        hs2.insert(306, "orange");
        hs.delete(12, "brown");

        assertEquals(hs.table, hs2.table);
    }

    @Test
    public void testDelete2() {
        hashArr hs = new hashArr(500);
        hashArr hs2 = new hashArr(500);
        hs.insert(44, "blue");
        hs.delete(44, "blue");

        assertEquals(hs.table, hs2.table);
    }

    @Test
    public void testDelete3() {
        hashArr hs = new hashArr(500);
        hashArr hs2 = new hashArr(500);
        hs.insert(44, "blue");
        hs2.insert(44, "blue");
        hs.insert(809, "green");
        hs.delete(809, "green");

        assertEquals(hs.table, hs2.table);
    }

}
