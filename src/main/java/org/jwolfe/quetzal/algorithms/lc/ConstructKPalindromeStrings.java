package org.jwolfe.quetzal.algorithms.lc;

public class ConstructKPalindromeStrings {
    class Solution {
        public boolean canConstruct(String s, int k) {
            if (s.length() < k) {
                return false;
            }

            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int index = c - 'a';
                count[index]++;
            }

            int odds = 0;
            for (int i = 0; i < 26; i++) {
                if (count[i] % 2 == 1) {
                    odds++;
                }
            }

            if (odds > k) {
                return false;
            }

            return true;
        }
    }
}

//    1400. Construct K Palindrome Strings
//    Medium
//    Given a string s and an integer k, return true if you can use all the characters in s to construct k palindrome strings or false otherwise.
//
//
//
//    Example 1:
//
//    Input: s = "annabelle", k = 2
//    Output: true
//    Explanation: You can construct two palindromes using all characters in s.
//    Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
//    Example 2:
//
//    Input: s = "leetcode", k = 3
//    Output: false
//    Explanation: It is impossible to construct 3 palindromes using all the characters of s.
//    Example 3:
//
//    Input: s = "true", k = 4
//    Output: true
//    Explanation: The only possible solution is to put each character in a separate string.
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists of lowercase English letters.
//    1 <= k <= 105