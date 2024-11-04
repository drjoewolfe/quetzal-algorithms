package org.jwolfe.quetzal.algorithms.lc;

public class StringCompressionIII {
    class Solution {
        public String compressedString(String word) {
            if (word == null || word.length() == 0) {
                return word;
            }

            StringBuilder builder = new StringBuilder();

            int n = word.length();
            int i = 0;
            while (i < n) {
                char c = word.charAt(i);
                int count = 0;

                while (i < n
                        && c == word.charAt(i)
                        && count < 9) {
                    i++;
                    count++;
                }

                builder.append(count).append(c);
            }

            return builder.toString();
        }
    }
}

//    3163. String Compression III
//    Medium
//    Given a string word, compress it using the following algorithm:
//
//    Begin with an empty string comp. While word is not empty, use the following operation:
//    Remove a maximum length prefix of word made of a single character c repeating at most 9 times.
//    Append the length of the prefix followed by c to comp.
//    Return the string comp.
//
//
//
//    Example 1:
//
//    Input: word = "abcde"
//
//    Output: "1a1b1c1d1e"
//
//    Explanation:
//
//    Initially, comp = "". Apply the operation 5 times, choosing "a", "b", "c", "d", and "e" as the prefix in each operation.
//
//    For each prefix, append "1" followed by the character to comp.
//
//    Example 2:
//
//    Input: word = "aaaaaaaaaaaaaabb"
//
//    Output: "9a5a2b"
//
//    Explanation:
//
//    Initially, comp = "". Apply the operation 3 times, choosing "aaaaaaaaa", "aaaaa", and "bb" as the prefix in each operation.
//
//    For prefix "aaaaaaaaa", append "9" followed by "a" to comp.
//    For prefix "aaaaa", append "5" followed by "a" to comp.
//    For prefix "bb", append "2" followed by "b" to comp.
//
//
//    Constraints:
//
//    1 <= word.length <= 2 * 105
//    word consists only of lowercase English letters.