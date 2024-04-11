package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class RemoveKDigits {
    class Solution {
        public String removeKdigits(String num, int k) {
            if(num == null || num.length() == 0 || k < 1) {
                return num;
            }

            int n = num.length();
            if(n < k) {
                return "0";
            }

            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < n; i++) {
                char c = num.charAt(i);

                while(k > 0
                        && !stack.isEmpty()
                        && stack.peek() > c) {
                    stack.pop();
                    k--;
                }

                stack.push(c);
            }

            while(k > 0) {
                stack.pop();
                k--;
            }

            StringBuilder builder = new StringBuilder();
            while(!stack.isEmpty()) {
                builder.append(stack.pop());
            }

            builder.reverse();

            while(builder.length() > 0 && builder.charAt(0) == '0') {
                builder.deleteCharAt(0);
            }

            if(builder.length() == 0) {
                return "0";
            }

            return builder.toString();
        }
    }

    class Solution_TLE_1 {
        public String removeKdigits(String num, int k) {
            if(num == null || num.length() == 0 || k < 1) {
                return num;
            }

            int n = num.length();
            if(n < k) {
                return "0";
            }

            StringBuilder builder = new StringBuilder(num);
            while(k > 0) {
                n = builder.length();
                int i = 0;

                while(i < n - 1
                        && builder.charAt(i) <= builder.charAt(i + 1)) {
                    i++;
                }

                builder.deleteCharAt(i);

                k--;
            }

            while(builder.length() > 0 && builder.charAt(0) == '0') {
                builder.deleteCharAt(0);
            }

            if(builder.length() == 0) {
                return "0";
            }

            return builder.toString();
        }
    }

    class Solution_Approach_2 {
        public String removeKdigits(String num, int k) {
            if(num == null || num.length() == 0 || k == 0) {
                return num;
            }

            int n = num.length();
            if(n < k) {
                return "";
            }

            StringBuilder sb = new StringBuilder(num);
            for(int j = 0; j < k; j++) {
                int i = 0;
                while(i < sb.length() - 1
                        && sb.charAt(i) <= sb.charAt(i + 1)) {
                    i++;
                }

                sb.delete(i, i  + 1);
            }

            while(sb.length() > 0 && sb.charAt(0) == '0') {
                sb.delete(0, 1);
            }

            if(sb.length() == 0) {
                return "0";
            }

            return sb.toString();
        }
    }

    class Solution_Approach1 {
        public String removeKdigits(String num, int k) {
            if(num == null || num.length() == 0 || k == 0) {
                return num;
            }

            int n = num.length();
            if(n < k) {
                return "";
            }

            int i = 0;
            for(; i < n - 1; i++) {
                if(num.charAt(i) > num.charAt(i + 1)) {
                    break;
                }
            }

            StringBuilder splicedNum = new StringBuilder();
            boolean spliceStarted = false;

            for(int j = 0; j < n; j++) {
                if(j == i) {
                    continue;
                }

                if(!spliceStarted && num.charAt(j) == '0') {
                    continue;
                }

                spliceStarted = true;
                splicedNum.append(num.charAt(j));
            }

            if(splicedNum.length() == 0) {
                splicedNum.append("0");
            }


            // String splicedNum = num.substring(0, i) + num.substring(i + 1, n);
            return removeKdigits(splicedNum.toString(), k - 1);
        }
    }

// "1432219"
// 3
}

//    402. Remove K Digits
//    Medium
//    Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.
//
//
//
//    Example 1:
//
//    Input: num = "1432219", k = 3
//    Output: "1219"
//    Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
//    Example 2:
//
//    Input: num = "10200", k = 1
//    Output: "200"
//    Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
//    Example 3:
//
//    Input: num = "10", k = 2
//    Output: "0"
//    Explanation: Remove all the digits from the number and it is left with nothing which is 0.
//
//
//    Constraints:
//
//    1 <= k <= num.length <= 105
//    num consists of only digits.
//    num does not have any leading zeros except for the zero itself.