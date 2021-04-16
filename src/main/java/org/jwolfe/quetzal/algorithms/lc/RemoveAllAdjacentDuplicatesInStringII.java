package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class RemoveAllAdjacentDuplicatesInStringII {
    class Solution {
        public String removeDuplicates(String s, int k) {
            if(s == null || s.length() < k) {
                return s;
            }

            if(k == 1) {
                return "";
            }

            Stack<CharacterFrequency> stack = new Stack<>();
            stack.push(new CharacterFrequency(s.charAt(0), 1));

            for(int i = 1; i < s.length(); i++) {
                char c = s.charAt(i);

                if(!stack.isEmpty() && stack.peek().c == c) {
                    var cf = stack.peek();
                    if(cf.frequency == k - 1) {
                        stack.pop();
                    } else {
                        cf.frequency++;
                    }

                } else {
                    stack.push(new CharacterFrequency(c, 1));
                }
            }

            StringBuilder builder = new StringBuilder();
            while(!stack.isEmpty()) {
                var cf = stack.pop();
                while(cf.frequency > 0) {
                    builder.append(cf.c);
                    cf.frequency--;
                }
            }

            return builder.reverse().toString();
        }
    }

    private static class CharacterFrequency {
        char c;
        int frequency;

        public CharacterFrequency(char c, int frequency) {
            this.c = c;
            this.frequency = frequency;
        }
    }
}

//    1209. Remove All Adjacent Duplicates in String II
//    Medium
//    Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.
//
//    We repeatedly make k duplicate removals on s until we no longer can.
//
//    Return the final string after all such duplicate removals have been made.
//
//    It is guaranteed that the answer is unique.
//
//
//
//    Example 1:
//
//    Input: s = "abcd", k = 2
//    Output: "abcd"
//    Explanation: There's nothing to delete.
//    Example 2:
//
//    Input: s = "deeedbbcccbdaa", k = 3
//    Output: "aa"
//    Explanation:
//    First delete "eee" and "ccc", get "ddbbbdaa"
//    Then delete "bbb", get "dddaa"
//    Finally delete "ddd", get "aa"
//    Example 3:
//
//    Input: s = "pbbcggttciiippooaais", k = 2
//    Output: "ps"
//
//
//    Constraints:
//
//    1 <= s.length <= 10^5
//    2 <= k <= 10^4
//    s only contains lower case English letters.