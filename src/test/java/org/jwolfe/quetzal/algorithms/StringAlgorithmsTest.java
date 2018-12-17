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
}