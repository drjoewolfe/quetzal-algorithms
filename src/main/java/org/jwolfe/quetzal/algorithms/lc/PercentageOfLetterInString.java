package org.jwolfe.quetzal.algorithms.lc;

public class PercentageOfLetterInString {
    class Solution {
        public int percentageLetter(String s, char letter) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int count = 0;
            int n = s.length();
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if(c == letter) {
                    count++;
                }
            }

            return (int) ((1d * count / n) * 100);
        }
    }
}

//    2278. Percentage of Letter in String
//    Easy
//    Given a string s and a character letter, return the percentage of characters in s that equal letter rounded down to the nearest whole percent.
//
//
//
//    Example 1:
//
//    Input: s = "foobar", letter = "o"
//    Output: 33
//    Explanation:
//    The percentage of characters in s that equal the letter 'o' is 2 / 6 * 100% = 33% when rounded down, so we return 33.
//    Example 2:
//
//    Input: s = "jjjj", letter = "k"
//    Output: 0
//    Explanation:
//    The percentage of characters in s that equal the letter 'k' is 0%, so we return 0.
//
//
//    Constraints:
//
//    1 <= s.length <= 100
//    s consists of lowercase English letters.
//    letter is a lowercase English letter.