package org.jwolfe.quetzal.algorithms.lc;

public class Largest3SameDigitNumberInString {
    class Solution {
        public String largestGoodInteger(String num) {
            if (num == null || num.length() < 3) {
                return "";
            }

            int n = num.length();
            int maxGoodNumber = 0;
            String maxGoodString = "";
            for (int i = 0; i < n - 2; i++) {
                int a = num.charAt(i) - '0';
                int b = num.charAt(i + 1) - '0';
                int c = num.charAt(i + 2) - '0';

                if (a == b && b == c) {
                    int number = a * 100 + b * 10 + c;
                    if (number >= maxGoodNumber) {
                        maxGoodNumber = number;
                        maxGoodString = Integer.toString(a) + Integer.toString(a) + Integer.toString(a);
                    }
                }
            }

            return maxGoodString;
        }
    }

    class Solution_Correct_2 {
        public String largestGoodInteger(String num) {
            if (num == null || num.length() < 3) {
                return "";
            }

            int n = num.length();
            char mc = ' ';
            for (int i = 2; i < n; i++) {
                char c1 = num.charAt(i - 2);
                char c2 = num.charAt(i - 1);
                char c3 = num.charAt(i);

                if (c1 == c2 && c2 == c3) {
                    // Good

                    if (mc < c1) {
                        mc = c1;
                    }
                }
            }

            return mc == ' ' ? "" : String.valueOf(new char[]{mc, mc, mc});
        }
    }

    class Solution_Correct_1 {
        public String largestGoodInteger(String num) {
            if (num == null || num.length() < 3) {
                return "";
            }

            int largestGood = Integer.MIN_VALUE;
            char[] largestGoodChars = new char[3];
            for (int i = 0; i <= num.length() - 3; i++) {
                char c1 = num.charAt(i);
                char c2 = num.charAt(i + 1);
                char c3 = num.charAt(i + 2);

                if (c1 == c2 && c2 == c3) {
                    // Good
                    int good = (c1 - '0') * 100 + (c2 - '0') * 10 + (c3 - '0');
                    if (largestGood < good) {
                        largestGood = good;
                        largestGoodChars[0] = c1;
                        largestGoodChars[1] = c2;
                        largestGoodChars[2] = c3;
                    }
                }
            }

            return largestGood != Integer.MIN_VALUE ? String.valueOf(largestGoodChars) : "";
        }
    }
}

//    2264. Largest 3-Same-Digit Number in String
//    Easy
//    You are given a string num representing a large integer. An integer is good if it meets the following conditions:
//
//    It is a substring of num with length 3.
//    It consists of only one unique digit.
//    Return the maximum good integer as a string or an empty string "" if no such integer exists.
//
//    Note:
//
//    A substring is a contiguous sequence of characters within a string.
//    There may be leading zeroes in num or a good integer.
//
//
//    Example 1:
//
//    Input: num = "6777133339"
//    Output: "777"
//    Explanation: There are two distinct good integers: "777" and "333".
//    "777" is the largest, so we return "777".
//    Example 2:
//
//    Input: num = "2300019"
//    Output: "000"
//    Explanation: "000" is the only good integer.
//    Example 3:
//
//    Input: num = "42352338"
//    Output: ""
//    Explanation: No substring of length 3 consists of only one unique digit. Therefore, there are no good integers.
//
//
//    Constraints:
//
//    3 <= num.length <= 1000
//    num only consists of digits.