package org.jwolfe.quetzal.algorithms.lc;

public class CheckIfOneStringSwapCanMakeStringsEqual {
    class Solution {
        public boolean areAlmostEqual(String s1, String s2) {
            if(s1 == null || s2 == null || s1.length() != s2.length()) {
                return false;
            }

            int diffCount = 0;
            int index1 = -1;
            int index2 = -1;
            for(int i = 0; i < s1.length(); i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);

                if(c1 != c2) {
                    diffCount++;

                    if(diffCount > 2) {
                        return false;
                    }

                    if(index1 == -1) {
                        index1 = i;
                    } else {
                        index2 = i;
                    }
                }
            }

            if(diffCount == 0) {
                return true;
            }


            return diffCount == 2 && s1.charAt(index1) == s2.charAt(index2) && s1.charAt(index2) == s2.charAt(index1);
        }
    }

// "bank"
// "kanb"

// "caa"
// "aaz"
}

//    1790. Check if One String Swap Can Make Strings Equal
//    Easy
//    You are given two strings s1 and s2 of equal length. A string swap is an operation where you choose two indices in a string (not necessarily different) and swap the characters at these indices.
//
//    Return true if it is possible to make both strings equal by performing at most one string swap on exactly one of the strings. Otherwise, return false.
//
//
//
//    Example 1:
//
//    Input: s1 = "bank", s2 = "kanb"
//    Output: true
//    Explanation: For example, swap the first character with the last character of s2 to make "bank".
//    Example 2:
//
//    Input: s1 = "attack", s2 = "defend"
//    Output: false
//    Explanation: It is impossible to make them equal with one string swap.
//    Example 3:
//
//    Input: s1 = "kelb", s2 = "kelb"
//    Output: true
//    Explanation: The two strings are already equal, so no string swap operation is required.
//
//
//    Constraints:
//
//    1 <= s1.length, s2.length <= 100
//    s1.length == s2.length
//    s1 and s2 consist of only lowercase English letters.