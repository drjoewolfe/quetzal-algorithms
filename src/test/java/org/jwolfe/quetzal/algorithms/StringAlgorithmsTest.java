package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;

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
    void getSumString() {
    	assertEquals("60", StringAlgorithms.getSumString("24",  "36"));
    	assertEquals("10", StringAlgorithms.getSumString("1",  "9"));
    	assertEquals("8", StringAlgorithms.getSumString("6",  "2"));
    	assertEquals("225", StringAlgorithms.getSumString("111",  "114"));
    }

    @Test
    void getUniqueCharacterCount() {
        assertEquals(5,  StringAlgorithms.getUniqueCharacterCount("abcde"));
        assertEquals(5, StringAlgorithms.getUniqueCharacterCount("aabbccddee"));
    }

    @Test
    void getUniqueCharacterCountSB() {
        assertEquals(5,  StringAlgorithms.getUniqueCharacterCount(new StringBuilder("abcde")));
        assertEquals(5, StringAlgorithms.getUniqueCharacterCount(new StringBuilder("aabbccddee")));
    }
}