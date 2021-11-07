package org.jwolfe.quetzal.algorithms.lc;

public class MultiplyStrings {
    class Solution {
        public String multiply(String num1, String num2) {
            if(num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) {
                return null;
            }

            int m = num1.length();
            int n = num2.length();

            int[] arr = new int[m + n];
            for(int i = m - 1; i >= 0; i--) {
                int a = num1.charAt(i) - '0';
                for(int j = n - 1; j >= 0; j--) {
                    int b = num2.charAt(j) - '0';

                    int c = a * b;
                    int index = i + j + 1;

                    arr[index] += c;
                }
            }

            StringBuilder builder = new StringBuilder();
            int carry = 0;
            for(int i = m + n - 1; i > 0; i--) {
                int val = arr[i] + carry;

                carry = val / 10;
                val %= 10;

                builder.append(val);
            }

            if(carry > 0) {
                builder.append(carry);
            }

            while(builder.length() > 0 && builder.charAt(builder.length() - 1) == '0') {
                builder.deleteCharAt(builder.length() - 1);
            }

            if(builder.length() == 0) {
                return "0";
            }

            return builder.reverse().toString();
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public String multiply(String num1, String num2) {
            if(num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
                return null;
            }

            int m = num1.length();
            int n = num2.length();

            int[] products = new int[m + n];

            for(int i = 0; i < m; i++) {
                int a = num1.charAt(i) - '0';
                for(int j = 0; j < n; j++) {
                    int b = num2.charAt(j) - '0';
                    int c = a * b;

                    products[i + j + 1] += c;
                }
            }

            StringBuilder answer = new StringBuilder();
            int carry = 0;
            for(int i = m + n - 1; i >= 0; i--) {
                int c = products[i] + carry;

                carry = c / 10;
                answer.append(c % 10);
            }

            while(answer.length() > 0 && answer.charAt(answer.length() - 1) == '0') {
                answer.deleteCharAt(answer.length() - 1);
            }

            if(answer.length() == 0) {
                return "0";
            }

            return answer.reverse().toString();
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }


// "2"
// "3"
}

//    43. Multiply Strings
//    Medium
//    Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
//
//    Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
//
//
//
//    Example 1:
//
//    Input: num1 = "2", num2 = "3"
//    Output: "6"
//    Example 2:
//
//    Input: num1 = "123", num2 = "456"
//    Output: "56088"
//
//
//    Constraints:
//
//    1 <= num1.length, num2.length <= 200
//    num1 and num2 consist of digits only.
//    Both num1 and num2 do not contain any leading zero, except the number 0 itself.