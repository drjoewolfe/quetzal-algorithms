package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.jwolfe.quetzal.test.QuetzalAssertions.*;

class StringAlgorithmsTest {

    @Test
    void getAllSubsequences() {
        String str;
        Set<String> subsequences;
        Set<String> expectedSequences;

        str = "abc";
        subsequences = StringAlgorithms.getAllSubsequences(str);
        assertEquals(7, subsequences.size());
        expectedSequences = Utilities.constructSet("a", "b", "c", "ab", "bc", "ac", "abc");
        assertSetEquals(expectedSequences, subsequences);

        str = "aaa";
        subsequences = StringAlgorithms.getAllSubsequences(str);
        assertEquals(3, subsequences.size());
        expectedSequences = Utilities.constructSet("a", "aa", "aaa");
        assertSetEquals(expectedSequences, subsequences);

        str = "aabc";
        subsequences = StringAlgorithms.getAllSubsequences(str);
        assertEquals(11, subsequences.size());
        expectedSequences = Utilities.constructSet("aa", "a", "ab", "bc", "ac", "b", "aac", "abc", "c", "aab", "aabc");
        assertSetEquals(expectedSequences, subsequences);
    }

    @Test
    void getAllSubsequencesIterative() {
        String str;
        Set<String> subsequences;
        Set<String> expectedSequences;

        str = "abc";
        subsequences = StringAlgorithms.getAllSubsequencesIterative(str);
        assertEquals(7, subsequences.size());
        expectedSequences = Utilities.constructSet("a", "b", "c", "ab", "bc", "ac", "abc");
        assertSetEquals(expectedSequences, subsequences);

        str = "aaa";
        subsequences = StringAlgorithms.getAllSubsequencesIterative(str);
        assertEquals(3, subsequences.size());
        expectedSequences = Utilities.constructSet("a", "aa", "aaa");
        assertSetEquals(expectedSequences, subsequences);

        str = "aabc";
        subsequences = StringAlgorithms.getAllSubsequencesIterative(str);
        assertEquals(11, subsequences.size());
        expectedSequences = Utilities.constructSet("aa", "a", "ab", "bc", "ac", "b", "aac", "abc", "c", "aab", "aabc");
        assertSetEquals(expectedSequences, subsequences);
    }

    @Test
    void getAllSubstrings() {
        String str;
        Set<String> subStrings;
        Set<String> expectedSubstrings;

        str = "abcd";
        subStrings = StringAlgorithms.getAllSubstrings(str);
        assertEquals(10, subStrings.size());
        expectedSubstrings = Utilities.constructSet("a", "b", "c", "d", "ab", "bc", "cd", "abc", "bcd", "abcd");
        assertSetEquals(expectedSubstrings, subStrings);
    }

    @Test
    void getAllSubStringsInLexicographicOrder() {
        String str;
        List<String> subStrings;
        List<String> expectedSubstrings;

        str = "abc";
        subStrings = StringAlgorithms.getAllSubStringsInLexicographicOrder(str);
        expectedSubstrings = Utilities.constructList("", "a", "ab", "abc", "ac", "b", "bc", "c");
        assertListStrictEquals(expectedSubstrings, subStrings);
    }

    @Test
    void getSumString() {
        assertEquals("60", StringAlgorithms.getSumString("24", "36"));
        assertEquals("10", StringAlgorithms.getSumString("1", "9"));
        assertEquals("8", StringAlgorithms.getSumString("6", "2"));
        assertEquals("225", StringAlgorithms.getSumString("111", "114"));
    }

    @Test
    void getUniqueCharacterCount() {
        assertEquals(5, StringAlgorithms.getUniqueCharacterCount("abcde"));
        assertEquals(5, StringAlgorithms.getUniqueCharacterCount("aabbccddee"));
    }

    @Test
    void getUniqueCharacterCountSB() {
        assertEquals(5, StringAlgorithms.getUniqueCharacterCount(new StringBuilder("abcde")));
        assertEquals(5, StringAlgorithms.getUniqueCharacterCount(new StringBuilder("aabbccddee")));
    }

    @Test
    void isBinaryString() {
        assertTrue(StringAlgorithms.isBinaryString("111"));
        assertTrue(StringAlgorithms.isBinaryString("000"));
        assertTrue(StringAlgorithms.isBinaryString("101"));
        assertFalse(StringAlgorithms.isBinaryString("aaa"));
        assertFalse(StringAlgorithms.isBinaryString(""));
        assertFalse(StringAlgorithms.isBinaryString(null));
    }

    @Test
    void isPalidrome() {
        assertFalse(StringAlgorithms.isPalidrome(null));
        assertFalse(StringAlgorithms.isPalidrome(""));
        assertTrue(StringAlgorithms.isPalidrome("a"));
        assertTrue(StringAlgorithms.isPalidrome("aa"));
        assertFalse(StringAlgorithms.isPalidrome("ab"));
        assertTrue(StringAlgorithms.isPalidrome("aba"));
        assertFalse(StringAlgorithms.isPalidrome("aab"));
        assertTrue(StringAlgorithms.isPalidrome("malayalam"));
    }

    @Test
    void getAllPalindromePartitions() {
        String str;
        List<List<String>> partitions;
        List<List<String>> expectedPartitions;

        str = "abcdefghij";
        expectedPartitions = Utilities.constructList(Utilities.constructList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"));
        partitions = StringAlgorithms.getAllPalindromePartitions(str);
        assertListOfListEquals(expectedPartitions, partitions);

        str = "add";
        expectedPartitions = Utilities.constructList(Utilities.constructList("a", "d", "d"), Utilities.constructList("a", "dd"));
        partitions = StringAlgorithms.getAllPalindromePartitions(str);
        assertListOfListEquals(expectedPartitions, partitions);

        str = "doopy";
        expectedPartitions = Utilities.constructList(Utilities.constructList("d", "o", "o", "p", "y"), Utilities.constructList("d", "oo", "p", "y"));
        partitions = StringAlgorithms.getAllPalindromePartitions(str);
        assertListOfListEquals(expectedPartitions, partitions);

        str = "zakaz";
        expectedPartitions = Utilities.constructList(Utilities.constructList("z", "a", "k", "a", "z"), Utilities.constructList("z", "aka", "z"), Utilities.constructList("zakaz"));
        partitions = StringAlgorithms.getAllPalindromePartitions(str);
        assertListOfListEquals(expectedPartitions, partitions);
    }

    @Test
    void reverse() {
        assertEquals("cba", StringAlgorithms.reverse("abc"));
        assertEquals("1", StringAlgorithms.reverse("1"));
        assertEquals("abcd", StringAlgorithms.reverse("dcba"));
    }
}