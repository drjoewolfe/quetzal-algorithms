package org.jwolfe.quetzal.algorithms.lc;

public class CountingWordsWithAGivenPrefix {
    class Solution {
        public int prefixCount(String[] words, String pref) {
            if (words == null) {
                return 0;
            }

            int count = 0;
            for (String word : words) {
                if (word.startsWith(pref)) {
                    count++;
                }
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public int prefixCount(String[] words, String pref) {
            int count = 0;

            for (String word : words) {
                if (word.startsWith(pref)) {
                    count++;
                }
            }

            return count;
        }
    }
}

//    2185. Counting Words With a Given Prefix
//    Easy
//    You are given an array of strings words and a string pref.
//
//    Return the number of strings in words that contain pref as a prefix.
//
//    A prefix of a string s is any leading contiguous substring of s.
//
//
//
//    Example 1:
//
//    Input: words = ["pay","attention","practice","attend"], pref = "at"
//    Output: 2
//    Explanation: The 2 strings that contain "at" as a prefix are: "attention" and "attend".
//    Example 2:
//
//    Input: words = ["leetcode","win","loops","success"], pref = "code"
//    Output: 0
//    Explanation: There are no strings that contain "code" as a prefix.
//
//
//    Constraints:
//
//    1 <= words.length <= 100
//    1 <= words[i].length, pref.length <= 100
//    words[i] and pref consist of lowercase English letters.