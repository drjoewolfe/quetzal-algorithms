package org.jwolfe.quetzal.algorithms.lc;

public class CountTheNumberOfSpecialCharactersI {
    class Solution {
        public int numberOfSpecialChars(String word) {
            if(word == null || word.length() < 2) {
                return 0;
            }

            int[] lowerCases = new int[26];
            int[] upperCases = new int[26];

            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if(c >= 'a' && c <= 'z') {
                    lowerCases[c - 'a']++;
                } else if(c >= 'A' && c <= 'Z') {
                    upperCases[c - 'A']++;
                }
            }

            int count = 0;
            for(int i = 0; i < 26; i++) {
                if(lowerCases[i] > 0 && upperCases[i] > 0) {
                    count++;
                }
            }

            return count;
        }
    }
}

//    3120. Count the Number of Special Characters I
//    Easy
//    You are given a string word. A letter is called special if it appears both in lowercase and uppercase in word.
//
//    Return the number of special letters in word.
//
//
//
//    Example 1:
//
//    Input: word = "aaAbcBC"
//
//    Output: 3
//
//    Explanation:
//
//    The special characters in word are 'a', 'b', and 'c'.
//
//    Example 2:
//
//    Input: word = "abc"
//
//    Output: 0
//
//    Explanation:
//
//    No character in word appears in uppercase.
//
//    Example 3:
//
//    Input: word = "abBCab"
//
//    Output: 1
//
//    Explanation:
//
//    The only special character in word is 'b'.
//
//
//
//    Constraints:
//
//    1 <= word.length <= 50
//    word consists of only lowercase and uppercase English letters.