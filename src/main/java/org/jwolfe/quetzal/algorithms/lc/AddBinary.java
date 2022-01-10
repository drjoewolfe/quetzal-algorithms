package org.jwolfe.quetzal.algorithms.lc;

public class AddBinary {
    class Solution {
        public String addBinary(String a, String b) {
            if(a == null) {
                return b;
            }

            if( b == null) {
                return a;
            }

            int m = a.length();
            int n = b.length();

            int i = m - 1;
            int j = n - 1;

            StringBuilder result = new StringBuilder();
            int carry = 0;
            while(i >= 0 || j >= 0) {
                int sum = carry;
                if(i >= 0) {
                    sum += (a.charAt(i) - '0');
                    i--;
                }

                if(j >= 0) {
                    sum += (b.charAt(j) - '0');
                    j--;
                }

                result.append(sum % 2);
                carry = sum / 2;
            }

            if(carry > 0) {
                result.append(carry);
            }

            return result.reverse().toString();
        }
    }

    class Solution_Correct_1 {
        public String addBinary(String a, String b) {
            if(a == null || b == null) {
                return null;
            }

            int aIndex = a.length() - 1;
            int bIndex = b.length() - 1;

            int carry = 0;
            int sum = 0;
            StringBuilder builder = new StringBuilder();
            while(aIndex >= 0 || bIndex >= 0) {
                sum = carry;

                if(aIndex >= 0) {
                    char ac = a.charAt(aIndex);
                    if(ac == '1') {
                        sum += 1;
                    }

                    aIndex--;
                }

                if(bIndex >= 0) {
                    char bc = b.charAt(bIndex);
                    if(bc == '1') {
                        sum += 1;
                    }

                    bIndex--;
                }

                if(sum == 3) {
                    carry = 1;
                    builder.append('1');
                } else if(sum == 2) {
                    carry = 1;
                    builder.append('0');
                } else {
                    carry = 0;
                    builder.append(sum);
                }
            }

            if(carry == 1) {
                builder.append(carry);
            }

            return builder.reverse().toString();
        }
    }
}

//    67. Add Binary
//    Easy
//    Given two binary strings a and b, return their sum as a binary string.
//
//
//
//    Example 1:
//
//    Input: a = "11", b = "1"
//    Output: "100"
//    Example 2:
//
//    Input: a = "1010", b = "1011"
//    Output: "10101"
//
//
//    Constraints:
//
//    1 <= a.length, b.length <= 104
//    a and b consist only of '0' or '1' characters.
//    Each string does not contain leading zeros except for the zero itself.