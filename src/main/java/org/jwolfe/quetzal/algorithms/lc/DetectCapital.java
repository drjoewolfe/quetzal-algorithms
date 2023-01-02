package org.jwolfe.quetzal.algorithms.lc;

public class DetectCapital {
    class Solution {
        public boolean detectCapitalUse(String word) {
            if(word == null || word.length() < 2) {
                return true;
            }

            int count = 0;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if(Character.isUpperCase(c)) {
                    count++;
                }
            }

            return (count == 0)
                    || (count == word.length())
                    || (count == 1 && Character.isUpperCase(word.charAt(0)));
        }
    }

    class Solution_Correct_1 {
        public boolean detectCapitalUse(String word) {
            if(word == null || word.length() == 0) {
                return true;
            }

            int n  = word.length();
            int capitalCount = 0;

            for(int i = 0; i < n; i++) {
                char c = word.charAt(i);
                if(Character.isUpperCase(c)) {
                    capitalCount++;
                }
            }

            return (capitalCount == n)
                    || (capitalCount == 0)
                    || (capitalCount == 1 && Character.isUpperCase(word.charAt(0)));
        }
    }
}

//    520. Detect Capital
//    Easy
//    We define the usage of capitals in a word to be right when one of the following cases holds:
//
//    All letters in this word are capitals, like "USA".
//    All letters in this word are not capitals, like "leetcode".
//    Only the first letter in this word is capital, like "Google".
//    Given a string word, return true if the usage of capitals in it is right.
//
//
//
//    Example 1:
//
//    Input: word = "USA"
//    Output: true
//    Example 2:
//
//    Input: word = "FlaG"
//    Output: false
//
//
//    Constraints:
//
//    1 <= word.length <= 100
//    word consists of lowercase and uppercase English letters.