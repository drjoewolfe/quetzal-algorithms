package org.jwolfe.quetzal.algorithms.lc;

public class ToLowerCase {
    class Solution {
        public String toLowerCase(String str) {
            StringBuilder lower = new StringBuilder();
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if(c >= 'A' && c <= 'Z') {
                    lower.append((char) ('a' + (c - 'A')));
                } else {
                    lower.append(c);
                }
            }

            return lower.toString();
        }
    }
}

//    709. To Lower Case
//    Easy
//    Given a string s, return the string after replacing every uppercase letter with the same lowercase letter.
//
//
//
//    Example 1:
//
//    Input: s = "Hello"
//    Output: "hello"
//    Example 2:
//
//    Input: s = "here"
//    Output: "here"
//    Example 3:
//
//    Input: s = "LOVELY"
//    Output: "lovely"
//
//
//    Constraints:
//
//    1 <= s.length <= 100
//    s consists of printable ASCII characters.
