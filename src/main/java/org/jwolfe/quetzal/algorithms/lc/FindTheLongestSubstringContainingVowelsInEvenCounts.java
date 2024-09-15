package org.jwolfe.quetzal.algorithms.lc;

public class FindTheLongestSubstringContainingVowelsInEvenCounts {
    class Solution {
        public int findTheLongestSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int[] map = new int[32];
            for (int i = 0; i < 32; i++) {
                map[i] = -1;
            }

            int[] characterMap = new int[26];
            characterMap['a' - 'a'] = 1;
            characterMap['e' - 'a'] = 2;
            characterMap['i' - 'a'] = 4;
            characterMap['o' - 'a'] = 8;
            characterMap['u' - 'a'] = 16;

            int maxLength = 0;
            int prefixXor = 0;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int index = c - 'a';

                prefixXor ^= characterMap[index];
                if (map[prefixXor] == -1
                        && prefixXor != 0) {
                    map[prefixXor] = i;
                }

                maxLength = Math.max(maxLength, i - map[prefixXor]);
            }

            return maxLength;
        }
    }
}

//    1371. Find the Longest Substring Containing Vowels in Even Counts
//    Medium
//    Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.
//
//
//
//    Example 1:
//
//    Input: s = "eleetminicoworoep"
//    Output: 13
//    Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.
//    Example 2:
//
//    Input: s = "leetcodeisgreat"
//    Output: 5
//    Explanation: The longest substring is "leetc" which contains two e's.
//    Example 3:
//
//    Input: s = "bcbcbc"
//    Output: 6
//    Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.
//
//
//    Constraints:
//
//    1 <= s.length <= 5 x 10^5
//    s contains only lowercase English letters.