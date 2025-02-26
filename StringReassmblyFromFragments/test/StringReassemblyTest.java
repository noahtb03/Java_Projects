import static org.junit.Assert.assertEquals;
import org.junit.Test;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    //routine
    //@Author: Noah Bennett
    @Test
    public void combination_test1() {
        String str = "empire";
        String strTest = "empire";
        String str1 = "rewind";
        String str1Test = "rewind";
        String fin = "empirewind";
        int overlap = 2;
        String combo = StringReassembly.combination(str, str1, overlap);
        assertEquals(combo, fin);
        assertEquals(str, strTest);
        assertEquals(str1, str1Test);
        assertEquals(overlap, 2);
    }

    //longer routine
    @Test
    public void combination_test2() {
        String str = "OhioStateTester";
        String strTest = "OhioStateTester";
        String str1 = "StateTesterjobs";
        String str1Test = "StateTesterjobs";
        String fin = "OhioStateTesterjobs";
        int overlap = 11;
        String combo = StringReassembly.combination(str, str1, overlap);
        assertEquals(combo, fin);
        assertEquals(str, strTest);
        assertEquals(str1, str1Test);
        assertEquals(overlap, 11);
    }

    //test with spaces
    @Test
    public void combination_test3() {
        String str = "I love to run";
        String strTest = "I love to run";
        String str1 = "running is my passion";
        String str1Test = "running is my passion";
        String fin = "I love to running is my passion";
        int overlap = 3;
        String combo = StringReassembly.combination(str, str1, overlap);
        assertEquals(fin, combo);
        assertEquals(strTest, str);
        assertEquals(str1Test, str1);
        assertEquals(3, overlap);
    }

    //test with two of the same
    @Test
    public void combination_test4() {
        String str = "I love to run";
        String strTest = "I love to run";
        String str1 = "love to run";
        String str1Test = "love to run";
        String fin = "I love to run";
        int overlap = 11;
        String combo = StringReassembly.combination(str, str1, overlap);
        assertEquals(fin, combo);
        assertEquals(strTest, str);
        assertEquals(str1Test, str1);
        assertEquals(11, overlap);
    }

    //one character
    @Test
    public void combination_test5() {
        String str = "a";
        String strTest = "a";
        String str1 = "a";
        String str1Test = "a";
        String fin = "a";
        int overlap = 1;
        String combo = StringReassembly.combination(str, str1, overlap);
        assertEquals(fin, combo);
        assertEquals(strTest, str);
        assertEquals(str1Test, str1);
        assertEquals(1, overlap);
    }

    //one character
    @Test
    public void addToSetAvoidingSubstrings_test1() {
        String str = "a";
        String strTest = "a";
        Set<String> tempSet = new Set1L<>();
        tempSet.add(str);
        Set<String> tempSetTest = new Set1L<>();
        tempSetTest.add(strTest);
        StringReassembly.addToSetAvoidingSubstrings(tempSet, str);
        assertEquals(tempSet, tempSetTest);
        assertEquals(strTest, str);
    }

    //Test with the same ones
    @Test
    public void addToSetAvoidingSubstrings_test2() {
        String str = "The cow was large";
        String strTest = "The cow was large";
        Set<String> tempSet = new Set1L<>();
        tempSet.add(str);
        Set<String> tempSetTest = new Set1L<>();
        tempSetTest.add(strTest);
        StringReassembly.addToSetAvoidingSubstrings(tempSet, str);
        assertEquals(tempSet, tempSetTest);
        assertEquals(strTest, str);
    }

    //Two different strings of different lengths
    @Test
    public void addToSetAvoidingSubstrings_test3() {
        String str = "The cow was large";
        String strTest = "The cow was large";
        String str1 = "Pigs are pink";
        String str1Test = "Pigs are pink";
        Set<String> tempSet = new Set1L<>();
        tempSet.add(str1);
        Set<String> tempSetTest = new Set1L<>();
        tempSetTest.add(str);
        tempSetTest.add(str1);
        StringReassembly.addToSetAvoidingSubstrings(tempSet, str);
        assertEquals(tempSet, tempSetTest);
        assertEquals(strTest, str);
        assertEquals(str1Test, str1);
    }

    //Test with a string that is a substring
    @Test
    public void addToSetAvoidingSubstrings_test4() {
        String str = "The cow was large";
        String strTest = "The cow was large";
        String str1 = "cow";
        String str1Test = "cow";
        Set<String> tempSet = new Set1L<>();
        tempSet.add(str1);
        Set<String> tempSetTest = new Set1L<>();
        tempSetTest.add(str);
        StringReassembly.addToSetAvoidingSubstrings(tempSet, str);
        assertEquals(tempSet, tempSetTest);
        assertEquals(strTest, str);
        assertEquals(str1Test, str1);
    }

    //Try with multiple different strings some with substrings
    @Test
    public void addToSetAvoidingSubstrings_test5() {
        String str = "The cow was large";
        String strTest = "The cow was large";
        String str1 = "Sheep like grass";
        String str1Test = "Sheep like grass";
        String str2 = "The cow was larger than a whale";
        String str2Test = "The cow was larger than a whale";
        String str3 = "Hens lay eggs";
        String str3Test = "Hens lay eggs";
        String str4 = "The farm is lively";
        String str4Test = "The farm is lively";
        Set<String> tempSet = new Set1L<>();
        tempSet.add(str1);
        tempSet.add(str2);
        tempSet.add(str3);
        tempSet.add(str4);
        Set<String> tempSetTest = new Set1L<>();
        tempSetTest.add(str1);
        tempSetTest.add(str2);
        tempSetTest.add(str3);
        tempSetTest.add(str4);
        StringReassembly.addToSetAvoidingSubstrings(tempSet, str);
        assertEquals(tempSet, tempSetTest);
        assertEquals(strTest, str);
        assertEquals(str1Test, str1);
        assertEquals(str2Test, str2);
        assertEquals(str3Test, str3);
        assertEquals(str4Test, str4);
    }

    //try with multiple different strings
    @Test
    public void addToSetAvoidingSubstrings_test6() {
        String str = "It was raining hard";
        String strTest = "It was raining hard";
        String str1 = "Sheep like grass";
        String str1Test = "Sheep like grass";
        String str2 = "The cow was larger than a whale";
        String str2Test = "The cow was larger than a whale";
        String str3 = "Hens lay eggs";
        String str3Test = "Hens lay eggs";
        String str4 = "The farm is lively";
        String str4Test = "The farm is lively";
        Set<String> tempSet = new Set1L<>();
        tempSet.add(str1);
        tempSet.add(str2);
        tempSet.add(str3);
        tempSet.add(str4);
        Set<String> tempSetTest = new Set1L<>();
        tempSetTest.add(str);
        tempSetTest.add(str1);
        tempSetTest.add(str2);
        tempSetTest.add(str3);
        tempSetTest.add(str4);
        StringReassembly.addToSetAvoidingSubstrings(tempSet, str);
        assertEquals(tempSet, tempSetTest);
        assertEquals(strTest, str);
        assertEquals(str1Test, str1);
        assertEquals(str2Test, str2);
        assertEquals(str3Test, str3);
        assertEquals(str4Test, str4);
    }

    //test an empty file

    @Test
    public void linesFromInput_test1() {
        SimpleReader in = new SimpleReader1L("test/testcase1.txt");
        Set<String> tempSet = new Set1L<>();
        Set<String> tempSetTest = new Set1L<>();
        tempSet = StringReassembly.linesFromInput(in);
        assertEquals(tempSet, tempSetTest);
        in.close();
    }

    //test a routine with one string

    @Test
    public void linesFromInput_test2() {
        SimpleReader in = new SimpleReader1L("test/testcase2.txt");
        Set<String> tempSet = new Set1L<>();
        Set<String> tempSetTest = new Set1L<>();
        tempSetTest.add("import this string to text file");
        tempSet = StringReassembly.linesFromInput(in);
        assertEquals(tempSet, tempSetTest);
        in.close();
    }

    //Try routine with one substring

    @Test
    public void linesFromInput_test3() {
        SimpleReader in = new SimpleReader1L("test/testcase3.txt");
        Set<String> tempSet = new Set1L<>();
        Set<String> tempSetTest = new Set1L<>();
        tempSetTest.add("import this file");
        tempSetTest.add("when you are finished");
        tempSetTest.add("get some sleep");
        tempSetTest.add("you need it");
        tempSet = StringReassembly.linesFromInput(in);
        assertEquals(tempSetTest, tempSet);
        in.close();
    }

    //try routine with many strings some with substrings

    @Test
    public void linesFromInput_test4() {
        SimpleReader in = new SimpleReader1L("test/testcase4.txt");
        Set<String> tempSet = new Set1L<>();
        Set<String> tempSetTest = new Set1L<>();
        tempSetTest.add("Add these to the set");
        tempSetTest.add("if they aren't substrings");
        tempSetTest.add("because those don't belong");
        tempSetTest.add("make sure it's not a superstring");
        tempSetTest.add("they don't belong either");
        tempSetTest.add("doing work");
        tempSetTest.add("this is a longer test");
        tempSetTest.add("bigger one");
        tempSet = StringReassembly.linesFromInput(in);
        assertEquals(tempSetTest, tempSet);
        in.close();
    }

    //routine short string

    @Test
    public void printWithLineSeparators_test1() {
        SimpleWriter out = new SimpleWriter1L();
        String str = "horse~house";
        StringReassembly.printWithLineSeparators(str, out);
        out.close();
    }

    //multiple ~ characters

    @Test
    public void printWithLineSeparators_test2() {
        SimpleWriter out = new SimpleWriter1L();
        String str = "horse~house on the farm ~ next line ~.";
        StringReassembly.printWithLineSeparators(str, out);
        out.close();
    }

    //multiple ~ in a row at the beginning

    @Test
    public void printWithLineSeparators_test3() {
        SimpleWriter out = new SimpleWriter1L();
        String str = "~~~~~11111111~ next line ~. 2222222";
        StringReassembly.printWithLineSeparators(str, out);
        out.close();
    }

    //multiple ~ in two separate occasions

    @Test
    public void printWithLineSeparators_test4() {
        SimpleWriter out = new SimpleWriter1L();
        String str = "~~~~~11111111~ next line ~. 2222222~~~~~~~.";
        StringReassembly.printWithLineSeparators(str, out);
        out.close();
    }

}
