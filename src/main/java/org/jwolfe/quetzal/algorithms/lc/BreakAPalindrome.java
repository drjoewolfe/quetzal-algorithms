package org.jwolfe.quetzal.algorithms.lc;

public class BreakAPalindrome {
    class Solution {
        public String breakPalindrome(String palindrome) {
            if(palindrome == null || palindrome.length() < 2) {
                return "";
            }

            int n = palindrome.length();
            StringBuilder builder = new StringBuilder(palindrome);
            boolean isOddLength = (n % 2 != 0);

            for(int i = 0; i < n; i++) {
                if(isOddLength && i == n / 2) {
                    continue;
                }

                char c = palindrome.charAt(i);
                if(c != 'a') {
                    builder.setCharAt(i, 'a');
                    return builder.toString();
                }
            }

            // All 'a's
            builder.setCharAt(n - 1, 'b');

            return builder.toString();
        }
    }

    class Solution_Correct_1 {
        public String breakPalindrome(String palindrome) {
            if(palindrome == null || palindrome.length() < 2) {
                return "";
            }

            int n = palindrome.length();
            boolean isOddLength = (n % 2 != 0);

            StringBuilder builder = new StringBuilder(palindrome);
            for(int i = 0; i < n; i++) {
                if(isOddLength && i == n / 2) {
                    continue;
                }

                char c = palindrome.charAt(i);
                if(c != 'a') {
                    builder.setCharAt(i, 'a');
                    return builder.toString();
                }
            }

            // All 'a's
            builder.setCharAt(n - 1, 'b');
            return builder.toString();
        }
    }

// "abccba"
}

//    1328. Break a Palindrome
//    Medium
//    Given a palindromic string of lowercase English letters palindrome, replace exactly one character with any lowercase English letter so that the resulting string is not a palindrome and that it is the lexicographically smallest one possible.
//
//    Return the resulting string. If there is no way to replace a character to make it not a palindrome, return an empty string.
//
//    A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, a has a character strictly smaller than the corresponding character in b. For example, "abcc" is lexicographically smaller than "abcd" because the first position they differ is at the fourth character, and 'c' is smaller than 'd'.
//
//
//
//    Example 1:
//
//    Input: palindrome = "abccba"
//    Output: "aaccba"
//    Explanation: There are many ways to make "abccba" not a palindrome, such as "zbccba", "aaccba", and "abacba".
//    Of all the ways, "aaccba" is the lexicographically smallest.
//    Example 2:
//
//    Input: palindrome = "a"
//    Output: ""
//    Explanation: There is no way to replace a single character to make "a" not a palindrome, so return an empty string.
//
//
//    Constraints:
//
//    1 <= palindrome.length <= 1000
//    palindrome consists of only lowercase English letters.