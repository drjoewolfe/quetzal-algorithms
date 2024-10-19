package org.jwolfe.quetzal.algorithms.lc;

public class FindKthBitInNthBinaryString {
    class Solution {
        public char findKthBit(int n, int k) {
            if (n < 1 || k < 1) {
                return ' ';
            }

            int length = (1 << n) - 1;
            int inversionCount = 0;

            while (k > 1) {
                int middle = length / 2 + 1;
                if (k == middle) {
                    return (inversionCount % 2 == 0) ? '1' : '0';
                }

                if (k > middle) {
                    k = (length + 1 - k);
                    inversionCount++;
                }

                length /= 2;
            }

            return (inversionCount % 2 == 0) ? '0' : '1';
        }
    }

    class Solution_Correct_1 {
        public char findKthBit(int n, int k) {
            if (n < 1 || k < 1) {
                return ' ';
            }

            String str = generate(n);
            return str.charAt(k - 1);
        }

        private String generate(int n) {
            if (n == 1) {
                return "0";
            }

            String prev = generate(n - 1);
            String tail = invertAndReverse(prev);

            return prev + "1" + tail;
        }

        private String invertAndReverse(String s) {
            StringBuilder sb = new StringBuilder(s);
            int left = 0;
            int right = s.length() - 1;

            while (left <= right) {
                char c1 = s.charAt(left);
                char c2 = s.charAt(right);

                sb.setCharAt(left, c2 == '0' ? '1' : '0');
                sb.setCharAt(right, c1 == '0' ? '1' : '0');

                left++;
                right--;
            }

            return sb.toString();
        }
    }

    class Solution_Classic {
        public char findKthBit(int n, int k) {
            if (n < 1 || k < 1) {
                return ' ';
            }

            String str = generate(n);
            return str.charAt(k - 1);
        }

        private String generate(int n) {
            if (n == 1) {
                return "0";
            }

            StringBuilder sb = new StringBuilder();
            String prev = generate(n - 1);
            String inverted = invert(prev);
            String reversed = reverse(inverted);

            sb.append(prev);
            sb.append("1");
            sb.append(reversed);

            return sb.toString();
        }

        private String invert(String s) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }

            return sb.toString();
        }

        private String reverse(String s) {
            StringBuilder sb = new StringBuilder(s);
            int left = 0;
            int right = s.length() - 1;

            while (left < right) {
                swap(sb, left, right);
                left++;
                right--;
            }

            return sb.toString();
        }

        private void swap(StringBuilder sb, int l, int r) {
            char temp = sb.charAt(l);
            sb.setCharAt(l, sb.charAt(r));
            sb.setCharAt(r, temp);
        }
    }
}

//    1545. Find Kth Bit in Nth Binary String
//    Medium
//    Given two positive integers n and k, the binary string Sn is formed as follows:
//
//    S1 = "0"
//    Si = Si - 1 + "1" + reverse(invert(Si - 1)) for i > 1
//    Where + denotes the concatenation operation, reverse(x) returns the reversed string x, and invert(x) inverts all the bits in x (0 changes to 1 and 1 changes to 0).
//
//    For example, the first four strings in the above sequence are:
//
//    S1 = "0"
//    S2 = "011"
//    S3 = "0111001"
//    S4 = "011100110110001"
//    Return the kth bit in Sn. It is guaranteed that k is valid for the given n.
//
//
//
//    Example 1:
//
//    Input: n = 3, k = 1
//    Output: "0"
//    Explanation: S3 is "0111001".
//    The 1st bit is "0".
//    Example 2:
//
//    Input: n = 4, k = 11
//    Output: "1"
//    Explanation: S4 is "011100110110001".
//    The 11th bit is "1".
//
//
//    Constraints:
//
//    1 <= n <= 20
//    1 <= k <= 2n - 1