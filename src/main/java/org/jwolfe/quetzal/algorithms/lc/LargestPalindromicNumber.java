package org.jwolfe.quetzal.algorithms.lc;

public class LargestPalindromicNumber {
    class Solution {
        public String largestPalindromic(String num) {
            if(num == null || num.length() < 1) {
                return num;
            }

            int[] frequencies = new int[10];
            for(int i = 0; i < num.length(); i++) {
                frequencies[num.charAt(i) - '0']++;
            }

            StringBuilder builder = new StringBuilder();
            int largestSingle = -1;
            for(int i = 9; i >= 0; i--) {
                if(builder.length() == 0 && i == 0) {
                    continue;
                }

                while(frequencies[i] > 1) {
                    builder.append(i);
                    frequencies[i] -= 2;
                }

                if(largestSingle == -1
                        && frequencies[i] == 1) {
                    largestSingle = i;
                }
            }

            if(builder.length() == 0
                    && largestSingle == -1) {
                return "0";
            }

            String s = reverse(builder.toString());

            if(largestSingle != -1) {
                builder.append(largestSingle);
            }

            builder.append(s);
            return builder.toString();
        }

        private String reverse(String s) {
            int i = 0;
            int j = s.length() - 1;

            StringBuilder sb = new StringBuilder(s);
            while(i < j) {
                char temp = sb.charAt(i);
                sb.setCharAt(i, sb.charAt(j));
                sb.setCharAt(j, temp);

                i++;
                j--;
            }

            return sb.toString();
        }
    }
}

//    2384. Largest Palindromic Number
//    Medium
//    You are given a string num consisting of digits only.
//
//    Return the largest palindromic integer (in the form of a string) that can be formed using digits taken from num. It should not contain leading zeroes.
//
//    Notes:
//
//    You do not need to use all the digits of num, but you must use at least one digit.
//    The digits can be reordered.
//
//
//    Example 1:
//
//    Input: num = "444947137"
//    Output: "7449447"
//    Explanation:
//    Use the digits "4449477" from "444947137" to form the palindromic integer "7449447".
//    It can be shown that "7449447" is the largest palindromic integer that can be formed.
//    Example 2:
//
//    Input: num = "00009"
//    Output: "9"
//    Explanation:
//    It can be shown that "9" is the largest palindromic integer that can be formed.
//    Note that the integer returned should not contain leading zeroes.
//
//
//    Constraints:
//
//    1 <= num.length <= 105
//    num consists of digits.