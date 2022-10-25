package org.jwolfe.quetzal.algorithms.lc;

public class CheckIfTwoStringArraysAreEquivalent {
    class Solution {
        public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
            if(word1 == null || word2 == null || word1.length == 0 || word2.length == 0) {
                return false;
            }

            int m = word1.length;
            int n = word2.length;

            int i1 = 0;
            int i2 = 0;

            int j1 = 0;
            int j2 = 0;

            while(i1 < m && i2 < n) {
                char c1 = word1[i1].charAt(j1++);
                char c2 = word2[i2].charAt(j2++);

                if(c1 != c2) {
                    return false;
                }

                if(j1 == word1[i1].length()) {
                    i1++;
                    j1 = 0;
                }

                if(j2 == word2[i2].length()) {
                    i2++;
                    j2 = 0;
                }
            }

            if(i1 < m || i2 < n) {
                return false;
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
            if(word1 == null || word2 == null || word1.length == 0 || word2.length == 0) {
                return false;
            }

            StringBuilder w1 = new StringBuilder();
            StringBuilder w2 = new StringBuilder();

            for(String w : word1) {
                w1.append(w);
            }

            for(String w : word2) {
                w2.append(w);
            }

            return w1.toString().equals(w2.toString());
        }
    }

    class Solution_Approach1 {
        public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
            if(word1 == null || word2 == null || word1.length == 0 || word2.length == 0) {
                return false;
            }

            int i = 0;
            int j = 0;

            int wi = 0;
            int wj = 0;

            int m = word1.length;
            int n = word2.length;

            while(i < m && j < n) {
                char c1 = word1[i].charAt(wi++);
                char c2 = word2[j].charAt(wj++);

                if(c1 != c2) {
                    return false;
                }

                if(wi == word1[i].length()) {
                    i++;
                    wi = 0;
                }

                if(wj == word2[j].length()) {
                    j++;
                    wj = 0;
                }
            }

            if(i < m || j < n) {
                return false;
            }

            return true;
        }
    }
}

//    1662. Check If Two String Arrays are Equivalent
//    Easy
//    Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
//
//    A string is represented by an array if the array elements concatenated in order forms the string.
//
//
//
//    Example 1:
//
//    Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
//    Output: true
//    Explanation:
//    word1 represents string "ab" + "c" -> "abc"
//    word2 represents string "a" + "bc" -> "abc"
//    The strings are the same, so return true.
//    Example 2:
//
//    Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
//    Output: false
//    Example 3:
//
//    Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
//    Output: true
//
//
//    Constraints:
//
//    1 <= word1.length, word2.length <= 103
//    1 <= word1[i].length, word2[i].length <= 103
//    1 <= sum(word1[i].length), sum(word2[i].length) <= 103
//    word1[i] and word2[i] consist of lowercase letters.