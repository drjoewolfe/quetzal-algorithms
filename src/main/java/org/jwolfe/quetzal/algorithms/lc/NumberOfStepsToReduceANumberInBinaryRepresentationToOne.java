package org.jwolfe.quetzal.algorithms.lc;

public class NumberOfStepsToReduceANumberInBinaryRepresentationToOne {
    class Solution {
        public int numSteps(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int steps = 0;
            int n = s.length();
            StringBuilder sb = new StringBuilder(s);

            for (int i = n - 1; i > 0; i--) {
                char c = sb.charAt(i);

                if (c == '1') {
                    // odd
                    int j = i - 1;

                    while (j >= 0) {
                        char pc = sb.charAt(j);

                        if (pc == '1') {
                            sb.setCharAt(j, '0');
                            if (j == 0) {
                                steps++;
                            }

                        } else {
                            sb.setCharAt(j, '1');
                            break;
                        }

                        j--;
                    }

                    steps += 2;
                } else {
                    // even
                    steps++;
                }
            }

            return steps;
        }
    }

    class Solution_Correct_1 {
        public int numSteps(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int steps = 0;
            StringBuilder sb = new StringBuilder(s);
            for (int i = s.length() - 1; i > 0; i--) {
                if (sb.charAt(i) == '1') {
                    int j = i - 1;
                    while (j >= 0) {
                        if (sb.charAt(j) == '1') {
                            sb.setCharAt(j, '0');
                            if (j == 0) {
                                steps++;
                            }
                        } else {
                            sb.setCharAt(j, '1');
                            break;
                        }

                        j--;
                    }

                    steps += 2;
                } else {
                    steps++;
                }
            }

            return steps;
        }
    }

    class Solution_Standard {
        public int numSteps(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            long n = getNumber(s);
            int steps = 0;
            while (n > 1) {
                if (n % 2 == 0) {
                    n /= 2;
                } else {
                    n++;
                }

                steps++;
            }

            return steps;
        }

        private long getNumber(String s) {
            long number = 0;
            for (int i = 0; i < s.length(); i++) {
                number *= 2;
                number += (s.charAt(i) == '1' ? 1 : 0);
            }

            return number;
        }
    }

// "1101"
// "1111011110000011100000110001011011110010111001010111110001"
// "1111110011101010110011100100101110010100101110111010111110110010"
// "11000"
// "10"
}

//    1404. Number of Steps to Reduce a Number in Binary Representation to One
//    Medium
//    Given the binary representation of an integer as a string s, return the number of steps to reduce it to 1 under the following rules:
//
//    If the current number is even, you have to divide it by 2.
//
//    If the current number is odd, you have to add 1 to it.
//
//    It is guaranteed that you can always reach one for all test cases.
//
//
//
//    Example 1:
//
//    Input: s = "1101"
//    Output: 6
//    Explanation: "1101" corressponds to number 13 in their decimal representation.
//    Step 1) 13 is odd, add 1 and obtain 14.
//    Step 2) 14 is even, divide by 2 and obtain 7.
//    Step 3) 7 is odd, add 1 and obtain 8.
//    Step 4) 8 is even, divide by 2 and obtain 4.
//    Step 5) 4 is even, divide by 2 and obtain 2.
//    Step 6) 2 is even, divide by 2 and obtain 1.
//    Example 2:
//
//    Input: s = "10"
//    Output: 1
//    Explanation: "10" corressponds to number 2 in their decimal representation.
//    Step 1) 2 is even, divide by 2 and obtain 1.
//    Example 3:
//
//    Input: s = "1"
//    Output: 0
//
//
//    Constraints:
//
//    1 <= s.length <= 500
//    s consists of characters '0' or '1'
//    s[0] == '1'