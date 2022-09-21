package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class GreatestEnglishLetterInUpperAndLowerCase {
    class Solution {
        public String greatestLetter(String s) {
            if(s == null || s.length() < 2) {
                return "";
            }

            Set<Character> set = new HashSet<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                set.add(c);
            }

            for(int i = 25; i >= 0; i--) {
                char lower = (char) ('a' + i);
                char upper = (char) ('A' + i);

                if(set.contains(lower) && set.contains(upper)) {
                    return Character.toString(upper);
                }
            }

            return "";
        }
    }
}

//    2309. Greatest English Letter in Upper and Lower Case
//    Easy
//    Given a string of English letters s, return the greatest English letter which occurs as both a lowercase and uppercase letter in s. The returned letter should be in uppercase. If no such letter exists, return an empty string.
//
//    An English letter b is greater than another letter a if b appears after a in the English alphabet.
//
//
//
//    Example 1:
//
//    Input: s = "lEeTcOdE"
//    Output: "E"
//    Explanation:
//    The letter 'E' is the only letter to appear in both lower and upper case.
//    Example 2:
//
//    Input: s = "arRAzFif"
//    Output: "R"
//    Explanation:
//    The letter 'R' is the greatest letter to appear in both lower and upper case.
//    Note that 'A' and 'F' also appear in both lower and upper case, but 'R' is greater than 'F' or 'A'.
//    Example 3:
//
//    Input: s = "AbCdEfGhIjK"
//    Output: ""
//    Explanation:
//    There is no letter that appears in both lower and upper case.
//
//
//    Constraints:
//
//    1 <= s.length <= 1000
//    s consists of lowercase and uppercase English letters.