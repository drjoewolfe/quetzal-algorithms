package org.jwolfe.quetzal.algorithms.lc;

public class MaximumNumberOfVowelsInASubstringOfGivenLength {
    class Solution {
        public int maxVowels(String s, int k) {
            if(s == null || s.length() == 0 || k < 1) {
                return 0;
            }

            int maxVowels = 0;

            int i = 0;
            int currentVowels = 0;
            for(int j = 0; j < s.length(); j++) {
                if(j - i == k) {
                    if(isVowel(s.charAt(i))) {
                        currentVowels--;
                    }

                    i++;
                }

                char c = s.charAt(j);
                if(isVowel(c)) {
                    currentVowels++;
                }

                maxVowels = Math.max(maxVowels, currentVowels);
            }

            return maxVowels;
        }

        private boolean isVowel(char c) {
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                return true;
            }

            return false;
        }
    }

    class Solution_Correct_1 {
        public int maxVowels(String s, int k) {
            if(s == null || s.length() == 0 || k < 1) {
                return 0;
            }

            int left = 0;
            int right = 0;

            int vowelCount = isVowel(s, 0) ? 1 : 0;
            int maxVowelCount = vowelCount;
            for(int i = 1; i < s.length(); i++) {
                right++;
                if(isVowel(s, right)) {
                    vowelCount++;
                }

                if(right - left + 1 > k) {
                    if(isVowel(s, left)) {
                        vowelCount--;
                    }

                    left++;
                }

                maxVowelCount = Math.max(maxVowelCount, vowelCount);
            }

            return maxVowelCount;
        }

        private boolean isVowel(String s, int index) {
            char c = s.charAt(index);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                    || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                return true;
            }

            return false;
        }
    }

// "abciiidef"
// 3

// "aeiou"
// 2
}

//    1456. Maximum Number of Vowels in a Substring of Given Length
//    Medium
//    Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
//
//    Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
//
//
//
//    Example 1:
//
//    Input: s = "abciiidef", k = 3
//    Output: 3
//    Explanation: The substring "iii" contains 3 vowel letters.
//    Example 2:
//
//    Input: s = "aeiou", k = 2
//    Output: 2
//    Explanation: Any substring of length 2 contains 2 vowels.
//    Example 3:
//
//    Input: s = "leetcode", k = 3
//    Output: 2
//    Explanation: "lee", "eet" and "ode" contain 2 vowels.
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists of lowercase English letters.
//    1 <= k <= s.length