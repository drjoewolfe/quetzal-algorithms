package org.jwolfe.quetzal.algorithms.lc;

public class LengthOfLastWord {
    class Solution {
        public int lengthOfLastWord(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            int length = 0;
            for(int i = n - 1; i >= 0; i--) {
                char c = s.charAt(i);

                if(c != ' ') {
                    length++;
                } else if(length > 0) {
                    break;
                }
            }

            return length;
        }
    }

    class Solution_Correct_1 {
        public int lengthOfLastWord(String s) {
            if(s == null) {
                return 0;
            }

            int lastIndex = s.length() - 1;
            int index = lastIndex;
            for(; index >= 0; index--) {
                if(s.charAt(index) == ' ') {
                    if(index == lastIndex) {
                        lastIndex--;
                    } else {
                        break;
                    }
                }
            }

            return lastIndex - index;
        }

        public int lengthOfLastWordApproach0(String s) {
            if(s == null) {
                return 0;
            }

            String[] words = s.split(" ");
            if(words.length == 0) {
                return 0;
            }

            return words[words.length - 1].length();
        }
    }

// "Hello World"
// "   fly me   to   the moon  "
}

//    58. Length of Last Word
//    Easy
//    Given a string s consisting of words and spaces, return the length of the last word in the string.
//
//    A word is a maximal substring consisting of non-space characters only.
//
//
//
//    Example 1:
//
//    Input: s = "Hello World"
//    Output: 5
//    Explanation: The last word is "World" with length 5.
//    Example 2:
//
//    Input: s = "   fly me   to   the moon  "
//    Output: 4
//    Explanation: The last word is "moon" with length 4.
//    Example 3:
//
//    Input: s = "luffy is still joyboy"
//    Output: 6
//    Explanation: The last word is "joyboy" with length 6.
//
//
//    Constraints:
//
//    1 <= s.length <= 104
//    s consists of only English letters and spaces ' '.
//    There will be at least one word in s.