package org.jwolfe.quetzal.algorithms.lc;

public class CheckIfAllCharactersHaveEqualNumberOfOccurrences {
    class Solution {
        public boolean areOccurrencesEqual(String s) {
            if(s == null || s.length() == 0) {
                return true;
            }

            int[] frequencies = new int[26];
            int max = 0;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int index = c - 'a';
                frequencies[index]++;

                max = Math.max(max, frequencies[index]);
            }

            for(int i = 0; i < 26; i++) {
                if(!(frequencies[i] == 0 || frequencies[i] == max)) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    1941. Check if All Characters Have Equal Number of Occurrences
//    Easy
//    Given a string s, return true if s is a good string, or false otherwise.
//
//    A string s is good if all the characters that appear in s have the same number of occurrences (i.e., the same frequency).
//
//
//
//    Example 1:
//
//    Input: s = "abacbc"
//    Output: true
//    Explanation: The characters that appear in s are 'a', 'b', and 'c'. All characters occur 2 times in s.
//    Example 2:
//
//    Input: s = "aaabb"
//    Output: false
//    Explanation: The characters that appear in s are 'a' and 'b'.
//    'a' occurs 3 times while 'b' occurs 2 times, which is not the same number of times.
//
//
//    Constraints:
//
//    1 <= s.length <= 1000
//    s consists of lowercase English letters.