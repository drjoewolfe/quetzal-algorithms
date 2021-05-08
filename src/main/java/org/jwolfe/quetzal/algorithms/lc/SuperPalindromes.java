package org.jwolfe.quetzal.algorithms.lc;

public class SuperPalindromes {
    class Solution {
        public int superpalindromesInRange(String left, String right) {
            if(left == null || right == null || left.length() == 0 || right.length() < left.length()) {
                return 0;
            }

            long l = Long.valueOf(left);
            long r = Long.valueOf(right);
            int maxRoot = 1_00_000;

            int count = 0;

            // Odd length
            for(int k = 1; k < maxRoot; k++) {
                String str = Integer.toString(k);
                String rootString = str + reverse(str).substring(1);
                Long root = Long.valueOf(rootString);
                Long number = root * root;

                if(number < l || number > r) {
                    continue;
                }

                if(isPalindrome(number)) {
                    count++;
                }

            }

            // Even length
            for(int k = 1; k < maxRoot; k++) {
                String str = Integer.toString(k);
                String rootString = str + reverse(str);
                Long root = Long.valueOf(rootString);
                Long number = root * root;

                if(number < l || number > r) {
                    continue;
                }

                if(isPalindrome(number)) {
                    count++;
                }

            }

            return count;
        }

        private String reverse(String str) {
            StringBuilder builder = new StringBuilder(str);
            return builder.reverse().toString();
        }

        private boolean isPalindrome(Long number) {
            return isPalindrome(Long.toString(number));
        }

        private boolean isPalindrome(String s) {
            int left = 0;
            int right = s.length() - 1;
            while(left < right) {
                if(s.charAt(left) != s.charAt(right)) {
                    return false;
                }

                left++;
                right--;
            }

            return true;
        }
    }

    class Solution_Brute {
        public int superpalindromesInRange(String left, String right) {
            if(left == null || right == null || left.length() == 0 || right.length() < left.length()) {
                return 0;
            }

            long l = Long.valueOf(left);
            long r = Long.valueOf(right);

            int count = 0;
            for(long number = l; number <= r; number++) {
                long root = (long) Math.sqrt(number);
                if(root * root != number) {
                    // Not a proper square root
                    continue;
                }

                if(isPalindrome(number) && isPalindrome(root)) {
                    // Super palindrome
                    count++;
                }
            }

            return count;
        }

        private boolean isPalindrome(long n) {
            String s = Long.toString(n);

            int left = 0;
            int right = s.length() - 1;
            while(left < right) {
                if(s.charAt(left) != s.charAt(right)) {
                    return false;
                }

                left++;
                right--;
            }

            return true;
        }
    }

// "4"
// "1000"
}

//    906. Super Palindromes
//    Hard
//    Let's say a positive integer is a super-palindrome if it is a palindrome, and it is also the square of a palindrome.
//
//    Given two positive integers left and right represented as strings, return the number of super-palindromes integers in the inclusive range [left, right].
//
//
//
//    Example 1:
//
//    Input: left = "4", right = "1000"
//    Output: 4
//    Explanation: 4, 9, 121, and 484 are superpalindromes.
//    Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.
//    Example 2:
//
//    Input: left = "1", right = "2"
//    Output: 1
//
//
//    Constraints:
//
//    1 <= left.length, right.length <= 18
//    left and right consist of only digits.
//    left and right cannot have leading zeros.
//    left and right represent integers in the range [1, 1018].
//    left is less than or equal to right.