package org.jwolfe.quetzal.algorithms.lc;

public class AddStrings {
    class Solution {
        public String addStrings(String num1, String num2) {
            if(num1 == null || num1.length() == 0) {
                return num2;
            }

            if(num2 == null || num2.length() == 0) {
                return num1;
            }

            StringBuilder builder = new StringBuilder();

            int i1 = num1.length() - 1;
            int i2 = num2.length() - 1;
            int carry = 0;

            while(i1 >= 0 || i2 >= 0) {
                int sum = carry;

                if(i1 >= 0) {
                    sum += num1.charAt(i1) - '0';
                }

                if(i2 >= 0) {
                    sum += num2.charAt(i2) - '0';
                }

                carry = sum / 10;
                sum %= 10;

                builder.append(sum);

                i1--;
                i2--;
            }

            if(carry > 0) {
                builder.append(carry);
            }

            return builder.reverse().toString();
        }
    }

    class Solution_Cut01 {
        public String addStrings(String num1, String num2) {
            if(num1 == null || num1.length() == 0) {
                return num2;
            }

            if(num2 == null || num2.length() == 0) {
                return num1;
            }

            StringBuilder builder = new StringBuilder();

            int i1 = num1.length() - 1;
            int i2 = num2.length() - 1;

            int carry = 0;
            int sum = 0;

            while(i1 >= 0 && i2 >= 0) {
                int d1 = num1.charAt(i1) - '0';
                int d2 = num2.charAt(i2) - '0';

                sum = carry;
                sum += d1;
                sum += d2;

                carry = sum / 10;
                sum %= 10;

                builder.append(sum);

                i1--;
                i2--;
            }

            while(i1 >= 0) {
                int d1 = num1.charAt(i1) - '0';
                sum = carry;
                sum += d1;

                carry = sum / 10;
                sum %= 10;

                builder.append(sum);
                i1--;
            }

            while(i2 >= 0) {
                int d2 = num2.charAt(i2) - '0';
                sum = carry;
                sum += d2;

                carry = sum / 10;
                sum %= 10;

                builder.append(sum);
                i2--;
            }

            if(carry > 0) {
                builder.append(carry);
            }

            return builder.reverse().toString();
        }
    }

    class Solution_Correct_2 {
        public String addStrings(String num1, String num2) {
            if(num1 == null && num2 == null) {
                return null;
            }

            if(num1 == null) {
                return num2;
            }

            if(num2 == null) {
                return num1;
            }

            int i = num1.length() - 1;
            int j = num2.length() - 1;

            StringBuilder builder = new StringBuilder();
            int carry = 0;
            while(i >= 0 || j >= 0) {
                int sum = carry;
                if(i >= 0) {
                    int a = num1.charAt(i) - '0';
                    sum += a;
                    i--;
                }

                if(j >= 0) {
                    int b = num2.charAt(j) - '0';
                    sum += b;
                    j--;
                }

                builder.append(sum % 10);
                carry = sum / 10;
            }

            if(carry > 0) {
                builder.append(carry);
            }

            return builder.reverse().toString();
        }
    }
}

//    415. Add Strings
//    Easy
//    Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
//
//    You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.
//
//
//
//    Example 1:
//
//    Input: num1 = "11", num2 = "123"
//    Output: "134"
//    Example 2:
//
//    Input: num1 = "456", num2 = "77"
//    Output: "533"
//    Example 3:
//
//    Input: num1 = "0", num2 = "0"
//    Output: "0"
//
//
//    Constraints:
//
//    1 <= num1.length, num2.length <= 104
//    num1 and num2 consist of only digits.
//    num1 and num2 don't have any leading zeros except for the zero itself.