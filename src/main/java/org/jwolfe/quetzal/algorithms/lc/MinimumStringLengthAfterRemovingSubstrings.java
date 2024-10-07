package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class MinimumStringLengthAfterRemovingSubstrings {
    class Solution {
        public int minLength(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (stack.isEmpty()) {
                    stack.push(c);
                } else {
                    char pc = stack.peek();

                    if ((pc == 'A' && c == 'B') || (pc == 'C' && c == 'D')) {
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                }
            }

            return stack.size();
        }
    }

    class Solution_Correct_1 {
        public int minLength(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < n; i++) {
                char cc = s.charAt(i);

                if (stack.isEmpty()) {
                    stack.push(cc);
                } else {

                    char pc = stack.peek();

                    if ((cc == 'B' && pc == 'A') || (cc == 'D' && pc == 'C')) {
                        stack.pop();
                    } else {
                        stack.push(cc);
                    }
                }
            }

            return stack.size();
        }
    }

    class Solution_Incorrect {
        public int minLength(String s) {
            if (s == null || s.length() == 2) {
                return 0;
            }

            int n = s.length();
            int pairs = 0;
            int i = 0;
            while (i < n - 1) {
                char cc = s.charAt(i);
                char nc = s.charAt(i + 1);

                if ((cc == 'A' && nc == 'B') || (cc == 'C' && nc == 'D')) {
                    pairs++;
                    i++;
                }

                i++;
            }

            return n - (pairs * 2);
        }
    }
}

//    2696. Minimum String Length After Removing Substrings
//    Easy
//    You are given a string s consisting only of uppercase English letters.
//
//    You can apply some operations to this string where, in one operation, you can remove any occurrence of one of the substrings "AB" or "CD" from s.
//
//    Return the minimum possible length of the resulting string that you can obtain.
//
//    Note that the string concatenates after removing the substring and could produce new "AB" or "CD" substrings.
//
//
//
//    Example 1:
//
//    Input: s = "ABFCACDB"
//    Output: 2
//    Explanation: We can do the following operations:
//    - Remove the substring "ABFCACDB", so s = "FCACDB".
//    - Remove the substring "FCACDB", so s = "FCAB".
//    - Remove the substring "FCAB", so s = "FC".
//    So the resulting length of the string is 2.
//    It can be shown that it is the minimum length that we can obtain.
//    Example 2:
//
//    Input: s = "ACBBD"
//    Output: 5
//    Explanation: We cannot do any operations on the string so the length remains the same.
//
//
//    Constraints:
//
//    1 <= s.length <= 100
//    s consists only of uppercase English letters.