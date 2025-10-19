package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LexicographicallySmallestStringAfterApplyingOperations {
    class Solution {
        public String findLexSmallestString(String s, int a, int b) {
            if (s == null || s.length() == 0) {
                return s;
            }

            Queue<String> queue = new LinkedList<>();
            queue.offer(s);

            Set<String> visited = new HashSet<>();
            visited.add(s);

            String smallestString = s;

            while (!queue.isEmpty()) {
                s = queue.poll();
                if (s.compareTo(smallestString) < 0) {
                    smallestString = s;
                }

                // Add
                StringBuilder addStringBuilder = new StringBuilder(s);
                for (int i = 1; i < s.length(); i += 2) {
                    char c = addStringBuilder.charAt(i);
                    int val = c - '0';
                    int nVal = (val + a) % 10;
                    char nc = (char) (nVal + '0');

                    addStringBuilder.setCharAt(i, nc);
                }

                String addString = addStringBuilder.toString();
                if (!visited.contains(addString)) {
                    queue.offer(addString);
                    visited.add(addString);
                }

                // Rotate
                String rotateString = rotate(s, b);
                if (!visited.contains(rotateString)) {
                    queue.offer(rotateString);
                    visited.add(rotateString);
                }
            }

            return smallestString;
        }

        private String rotate(String s, int steps) {
            int n = s.length();

            steps = steps % n;
            int end = n - 1;

            StringBuilder builder = new StringBuilder(s);
            reverse(builder, 0, end);
            reverse(builder, 0, steps - 1);
            reverse(builder, steps, end);

            return builder.toString();
        }

        private void reverse(StringBuilder builder, int left, int right) {
            while (left < right) {
                char lc = builder.charAt(left);
                builder.setCharAt(left, builder.charAt(right));
                builder.setCharAt(right, lc);

                left++;
                right--;
            }
        }
    }
}

//    1625. Lexicographically Smallest String After Applying Operations
//    Medium
//    You are given a string s of even length consisting of digits from 0 to 9, and two integers a and b.
//
//    You can apply either of the following two operations any number of times and in any order on s:
//
//    Add a to all odd indices of s (0-indexed). Digits post 9 are cycled back to 0. For example, if s = "3456" and a = 5, s becomes "3951".
//    Rotate s to the right by b positions. For example, if s = "3456" and b = 1, s becomes "6345".
//    Return the lexicographically smallest string you can obtain by applying the above operations any number of times on s.
//
//    A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b. For example, "0158" is lexicographically smaller than "0190" because the first position they differ is at the third letter, and '5' comes before '9'.
//
//
//
//    Example 1:
//
//    Input: s = "5525", a = 9, b = 2
//    Output: "2050"
//    Explanation: We can apply the following operations:
//    Start:  "5525"
//    Rotate: "2555"
//    Add:    "2454"
//    Add:    "2353"
//    Rotate: "5323"
//    Add:    "5222"
//    Add:    "5121"
//    Rotate: "2151"
//    Add:    "2050"​​​​​
//    There is no way to obtain a string that is lexicographically smaller than "2050".
//    Example 2:
//
//    Input: s = "74", a = 5, b = 1
//    Output: "24"
//    Explanation: We can apply the following operations:
//    Start:  "74"
//    Rotate: "47"
//    ​​​​​​​Add:    "42"
//    ​​​​​​​Rotate: "24"​​​​​​​​​​​​
//    There is no way to obtain a string that is lexicographically smaller than "24".
//    Example 3:
//
//    Input: s = "0011", a = 4, b = 2
//    Output: "0011"
//    Explanation: There are no sequence of operations that will give us a lexicographically smaller string than "0011".
//
//
//    Constraints:
//
//    2 <= s.length <= 100
//    s.length is even.
//    s consists of digits from 0 to 9 only.
//    1 <= a <= 9
//    1 <= b <= s.length - 1