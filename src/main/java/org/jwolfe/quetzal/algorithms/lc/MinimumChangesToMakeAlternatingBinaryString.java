package org.jwolfe.quetzal.algorithms.lc;

public class MinimumChangesToMakeAlternatingBinaryString {
    class Solution {
        public int minOperations(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            int operationsForStart0 = 0;
            int operationsForStart1 = 0;

            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if(i % 2 == 0) {
                    // even indexes -> 0, 2, 4, 6

                    // For start-0, entries should be 0
                    // For start-1, entries should be 1

                    if(c == '0') {
                        operationsForStart1++;
                    } else {
                        operationsForStart0++;
                    }
                } else {
                    // odd indexes -> 1, 3, 5, 7

                    // For start-0, entries should be 1
                    // For start-1, entries should be 0;

                    if(c == '0') {
                        operationsForStart0++;
                    } else {
                        operationsForStart1++;
                    }
                }
            }

            return Math.min(operationsForStart0, operationsForStart1);
        }
    }

    class Solution_Correct_1 {
        public int minOperations(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();
            int operations = 0;

            // Option 1: 01010101...
            char option1Char = '0';
            int option1Operations = 0;

            // Option 2: 10101010...
            char option2Char = '1';
            int option2Operations = 0;

            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);

                if(c == '0') {
                    if(option1Char == '1') {
                        option1Operations++;
                    }

                    if(option2Char == '1') {
                        option2Operations++;
                    }
                } else {
                    if(option1Char == '0') {
                        option1Operations++;
                    }

                    if(option2Char == '0') {
                        option2Operations++;
                    }
                }

                option1Char = (option1Char == '0') ? '1': '0';
                option2Char = (option2Char == '0') ? '1': '0';
            }

            return Math.min(option1Operations, option2Operations);
        }
    }

    class Solution_Incorrect {
        public int minOperations(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();
            boolean prevZero = s.charAt(0) == '0';
            int operations = 0;
            for(int i = 1; i < n; i++) {
                char c = s.charAt(i);
                if(c == '0') {
                    if(prevZero) {
                        operations++;
                        c = '1';
                    }
                } else {
                    if(!prevZero) {
                        operations++;
                        c = '0';
                    }
                }

                prevZero = c == '0';
            }

            return operations;
        }
    }

// "0100"
// "1111"
// "10"
}

//    1758. Minimum Changes To Make Alternating Binary String
//    Easy
//    You are given a string s consisting only of the characters '0' and '1'. In one operation, you can change any '0' to '1' or vice versa.
//
//    The string is called alternating if no two adjacent characters are equal. For example, the string "010" is alternating, while the string "0100" is not.
//
//    Return the minimum number of operations needed to make s alternating.
//
//
//
//    Example 1:
//
//    Input: s = "0100"
//    Output: 1
//    Explanation: If you change the last character to '1', s will be "0101", which is alternating.
//    Example 2:
//
//    Input: s = "10"
//    Output: 0
//    Explanation: s is already alternating.
//    Example 3:
//
//    Input: s = "1111"
//    Output: 2
//    Explanation: You need two operations to reach "0101" or "1010".
//
//
//    Constraints:
//
//    1 <= s.length <= 104
//    s[i] is either '0' or '1'.
